import { Component, OnInit } from '@angular/core';
import { PlayersService } from "../../../players/services/players.service";
import { CompetitionsService } from "../../services/competitions.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'app-competition-add',
  templateUrl: './competition-add.component.html',
  styleUrls: ['./competition-add.component.css']
})
export class CompetitionAddComponent implements OnInit {

  players: any[];
  form: FormGroup= new FormGroup({
    name: new FormControl('', [
      Validators.required
    ]),
    playerIds: new FormControl('', [
      Validators.minLength(2)
    ])
  });

  constructor(private playersService: PlayersService,
              private competitionsService: CompetitionsService,
              private router: Router) {}

  ngOnInit() {
    this.playersService.fetchList()
      .subscribe((resp:any)=>{
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
          console.log(resp);
          this.router.navigate(['/competitions/details', resp]);
        });
    } else {
      console.error('form invalid');
    }
  }


}
