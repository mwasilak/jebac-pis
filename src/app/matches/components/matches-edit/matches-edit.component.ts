import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from "@angular/router";
import { AbstractControl, FormArray, FormBuilder, FormGroup, ValidatorFn } from "@angular/forms";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { forkJoin } from "rxjs";

import { Match } from "../../match";
import { Player } from "../../../players/player";
import { Competition } from "../../../competition/competition";
import { MatchesService } from "../../services/matches.service";
import { PlayersService } from "../../../players/services/players.service";
import { CompetitionsService } from "../../../competition/services/competitions.service";

@Component({
  selector: 'app-matches-edit',
  templateUrl: './matches-edit.component.html',
  styleUrls: ['./matches-edit.component.css']
})
export class MatchesEditComponent implements OnInit, OnDestroy {

  @ViewChild('template', { static: true }) template

  modalRef: BsModalRef;
  form: FormGroup;
  games: FormArray;

  match: Match;
  player1: Player;
  player2: Player;
  competition: Competition;

  constructor(private matchesService:MatchesService,
              private playersService:PlayersService,
              private competitionsService:CompetitionsService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router,
              private modalService:BsModalService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {

      this.getData(params.get("id")).subscribe(responseList => {
        this.match = responseList[0];
        this.player1 = responseList[1][this.match.player1Id];
        this.player2 = responseList[1][this.match.player2Id];
        this.competition = responseList[2];

        this.form = this.formBuilder.group({
          games: this.formBuilder.array([])
        }, {
          validator: this.gameValidator(this.competition.victoryConditions.numberOfPointsToWin,this.competition.victoryConditions.numberOfWinsRequired)
        });

        this.games = this.form.get('games') as FormArray;
        for(let game of this.match.games) {
          this.games.push(this.createGame(game.scorePlayer1, game.scorePlayer2));
        }
        for (let i = 0; i < this.competition.victoryConditions.numberOfWinsRequired - this.match.games.length; i++) {
          this.games.push(this.createGame("",""));
        }
        this.modalRef = this.modalService.show(this.template, {});
        this.modalService.onHide.subscribe((reason: string) => {
          if(reason === "backdrop-click") {
            this.router.navigate(['', {outlets: {modal: null}}]);
          }
        })
      });
    });
  }

  ngOnDestroy() {
    this.modalRef.hide();
    this.modalRef = null;
  }

  getData(id:string) {

    let matchRequest = this.matchesService.fetchDetails(id);
    let playersRequest = this.playersService.fetchListByMatchId(id);
    let competitionRequest = this.competitionsService.fetchDetailsByMatchId(id);
    return forkJoin([matchRequest, playersRequest, competitionRequest]);
  }

  createGame(scorePlayer1, scorePlayer2) : FormGroup {
    return this.formBuilder.group({
      scorePlayer1: [scorePlayer1],
      scorePlayer2: [scorePlayer2]
    });
  }

  addGame(): void {
    this.games = this.form.get('games') as FormArray;
    this.games.push(this.createGame('', ''));
  }

  removeGame(i: number): void {
    this.games = this.form.get('games') as FormArray;
    this.games.removeAt(i);
  }

  sendForm(form) {
    if (form.valid) {
      this.matchesService
        .edit(this.match.id.toString(),{
          ...form.value
        })
        .subscribe((resp) => {
          this.router.routeReuseStrategy.shouldReuseRoute = (future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean => {
            return false;
          };
          this.router.navigate(['', {outlets: {modal: null}}]);
        });
    } else {
      console.error('form invalid');
    }
  }

  gameValidator(numberOfPointsToWin : number, numberOfWinsRequired: number):ValidatorFn {
    return (form: AbstractControl):
      { [key: string]: any } | null =>
    {
      let gamesArray = form.get('games') as FormArray;
      let index = 0;
      let player1Wins = 0;
      let player2Wins = 0;
      let errorList = {};
      for (let game of gamesArray.controls) {
        index += 1;
        let scorePlayer1 = game.get('scorePlayer1').value;
        let scorePlayer2 = game.get('scorePlayer2').value;
        if (typeof scorePlayer1 !== 'number' || typeof scorePlayer2 !== 'number'
          || scorePlayer1 === null || scorePlayer2 === null
          || scorePlayer1 % 1 !== 0 || scorePlayer2 % 1 !== 0) {
          errorList["game" + (index) + "invalidInput"] = true;
          continue;
        }
        if (((scorePlayer1 > scorePlayer2 + 1) && (scorePlayer1 == numberOfPointsToWin))
          || ((scorePlayer1 === scorePlayer2 + 2) && (scorePlayer1 >= numberOfPointsToWin))) {
          player1Wins += 1;
        } else if (((scorePlayer2 > scorePlayer1 + 1) && (scorePlayer2 == numberOfPointsToWin))
          || (scorePlayer2 === scorePlayer1 + 2 && (scorePlayer2 >= numberOfPointsToWin))) {
          player2Wins += 1;
        } else {
          errorList["game" + (index) + "ScoreIncorrect"] = true;
        }
      }
      if (((player1Wins > player2Wins && player1Wins != numberOfWinsRequired) ||
        (player2Wins > player1Wins && player2Wins != numberOfWinsRequired)) ||
        (player1Wins == player2Wins)) {
        errorList["incorrectNoOfWins"] = true;
      }
      return errorList;
    }
  }


}
