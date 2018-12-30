import { Component, OnInit } from '@angular/core';
import { Match } from "../../match";
import { MatchesService } from "../../services/matches.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-matches-edit',
  templateUrl: './matches-edit.component.html',
  styleUrls: ['./matches-edit.component.css']
})
export class MatchesEditComponent implements OnInit {

  match: Match = new Match();
  form: FormGroup;
  games: FormArray;

  constructor(private matchesService:MatchesService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {

      this.form = this.formBuilder.group({
        games: this.formBuilder.array([])
      });

      this.matchesService.fetchDetails(params.get("id")).subscribe((resp:any) => {
        this.match.id = resp['id'];
        this.match.name = resp['name'];
        this.match.player1 = resp['player1'];
        this.match.player2 = resp['player2'];
        this.match.games = resp['games'];
        this.match.bracketPosition = resp['bracketPosition'];
        this.match.resultRegistrationTime = resp['resultRegistrationTime'];
        for(let game of this.match.games) {
          this.games = this.form.get('games') as FormArray;
          this.games.push(this.createGame(game.scorePlayer1, game.scorePlayer2));
        }
      });
    });
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
          this.router.navigate(['/matches/details', resp]);
        });
    } else {
      console.error('form invalid');
    }
  }

}
