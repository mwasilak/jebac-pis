import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { PlayersService } from "../../services/players.service";
import { Player } from "../../player";

@Component({
  selector: 'app-players-details',
  templateUrl: './players-details.component.html',
  styleUrls: ['./players-details.component.css']
})
export class PlayersDetailsComponent implements OnInit {

  player: Player;

  constructor(private playersService:PlayersService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.playersService.fetchDetails(params.get("id"))
        .subscribe((resp:Player)=>{
          this.player = resp;
        });
    });
  }

}
