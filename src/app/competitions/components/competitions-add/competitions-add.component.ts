import { Component, OnInit } from '@angular/core';
import { PlayersService } from "../../../players/services/players.service";
import { CompetitionsService } from "../../services/competitions.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Player } from "../../../players/player";

@Component({
  selector: 'app-competitions-add',
  templateUrl: './competitions-add.component.html',
  styleUrls: ['./competitions-add.component.css']
})
export class CompetitionsAddComponent implements OnInit {

  players: Array<Player>;

  form: FormGroup = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(32)
    ]),
    playerIds: new FormControl([], [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(32)
    ]),
    numberOfWinsRequired: new FormControl(3, [
      Validators.required,
      Validators.min(1),
      Validators.max(5)
    ]),
    numberOfPointsToWin: new FormControl(11, [
      Validators.required,
      Validators.min(11),
      Validators.max(25)
    ])
  });

  constructor(private playersService: PlayersService,
              private competitionsService: CompetitionsService,
              private router: Router) {
  }

  ngOnInit() {
    this.playersService.fetchList()
      .subscribe((resp: Array<Player>) => {
        this.players = resp;
      });
  }

  sendForm(form) {
    if (form.valid) {
      this.competitionsService
        .add({
          ...form.value
        })
        .subscribe((resp) => {
          this.router.navigate(['/competitions/details', resp]).then();
        });
    } else {
      this.form.markAllAsTouched();
    }
  }


}
