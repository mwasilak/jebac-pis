import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from "@angular/router";
import { FormArray, FormBuilder, FormGroup } from "@angular/forms";
import { BsModalRef, BsModalService } from "ngx-bootstrap";
import { forkJoin } from "rxjs";

import { Match } from "../../match";
import { Player } from "../../../players/player";
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

  constructor(private matchesService:MatchesService,
              private playersService:PlayersService,
              private competitionsService:CompetitionsService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router,
              private modalService:BsModalService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {

      this.form = this.formBuilder.group({
        games: this.formBuilder.array([])
      });

      this.getData(params.get("id")).subscribe(responseList => {
        this.match = responseList[0];
        this.player1 = responseList[1][this.match.player1Id];
        this.player2 = responseList[1][this.match.player2Id];
        let competition = responseList[2];

        this.games = this.form.get('games') as FormArray;
        for(let game of this.match.games) {
          this.games.push(this.createGame(game.scorePlayer1, game.scorePlayer2));
        }
        for (let i = 0; i < competition.victoryConditions.numberOfWinsRequired - this.match.games.length; i++) {
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
          this.router.navigate(['', { outlets: { modal: ['matches', 'details', resp] } }]);
        });
    } else {
      console.error('form invalid');
    }
  }

}
