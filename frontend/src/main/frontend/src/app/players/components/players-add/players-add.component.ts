import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PlayersService} from "../../services/players.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-players-add',
  templateUrl: './players-add.component.html',
  styleUrls: ['./players-add.component.css']
})
export class PlayersAddComponent implements OnInit {

  form: FormGroup= new FormGroup({
    firstName: new FormControl('', [
      Validators.minLength(2)
    ]),
    lastName: new FormControl('', [
      Validators.minLength(2)
    ])
  });

  constructor(private playersService: PlayersService,
              private router: Router) { }

  ngOnInit() {
  }

  sendForm(form) {
    if (form.valid) {
      this.playersService
        .add({
          ...form.value
        })
        .subscribe((resp) => {
          this.router.navigate(['/players/details', resp]);
        });
    } else {
      console.error('form invalid');
    }
  }

}
