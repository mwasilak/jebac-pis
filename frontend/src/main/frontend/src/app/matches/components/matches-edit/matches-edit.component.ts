import { Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from "@angular/router";
import { FormArray, FormBuilder, FormGroup } from "@angular/forms";
import { BsModalRef, BsModalService } from "ngx-bootstrap";
import { forkJoin } from "rxjs";

import { Match } from "../../match";
import { MatchesService } from "../../services/matches.service";
import { PlayersService } from "../../../players/services/players.service";

@Component({
  selector: 'app-matches-edit',
  templateUrl: './matches-edit.component.html',
  styleUrls: ['./matches-edit.component.css']
})
export class MatchesEditComponent implements OnInit {

  @ViewChild('template') template

  modalRef: BsModalRef;
  match: Match = new Match();
  form: FormGroup;
  games: FormArray;

  constructor(private matchesService:MatchesService,
              private playersService:PlayersService,
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
        let matchResponse = responseList[0];
        let playersResponse = responseList[1];

        this.match.id = matchResponse['id'];
        this.match.name = matchResponse['name'];
        this.match.player1 = playersResponse[matchResponse['player1Id']];
        this.match.player2 = playersResponse[matchResponse['player2Id']];
        this.match.games = matchResponse['games'];
        this.match.bracketPosition = matchResponse['bracketPosition'];
        this.match.resultRegistrationTime = matchResponse['resultRegistrationTime'];
        for(let game of this.match.games) {
          this.games = this.form.get('games') as FormArray;
          this.games.push(this.createGame(game.scorePlayer1, game.scorePlayer2));
        }
        this.modalRef = this.modalService.show(this.template, {});
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
    return forkJoin([matchRequest, playersRequest]);
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
