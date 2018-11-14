import { Component, OnInit } from '@angular/core';
import {PlayersService} from "../../services/players.service";

@Component({
  selector: 'app-players-list',
  templateUrl: './players-list.component.html',
  styleUrls: ['./players-list.component.css']
})
export class PlayersListComponent implements OnInit {

  players: any[];

  constructor(private playersService:PlayersService) { }

  ngOnInit() {
    this.playersService.fetchList()
      .subscribe((resp:any)=>{
        this.players = resp;
      });
  }

}
