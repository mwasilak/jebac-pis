import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { MatchesService } from "../../services/matches.service";
import {Match} from "../../match";

@Component({
  selector: 'app-matches-details',
  templateUrl: './matches-details.component.html',
  styleUrls: ['./matches-details.component.css']
})
export class MatchesDetailsComponent implements OnInit {

  match: Match = new Match();

  constructor(private matchesService:MatchesService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.matchesService.fetchDetails(params.get("id"))
        .subscribe((resp:any)=>{
          this.match.id = resp['id'];
          this.match.name = resp['name'];
          this.match.player1 = resp['player1'];
          this.match.player2 = resp['player2'];
          this.match.rounds = resp['rounds'];
          this.match.position = resp['position'];
        });
    });
  }

}