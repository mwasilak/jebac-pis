import { Component, OnInit } from '@angular/core';
import {CompetitionsService} from "../../services/competitions.service";
import {ActivatedRoute} from "@angular/router";
import {Competition} from "../../competition";

@Component({
  selector: 'app-competition-details',
  templateUrl: './competition-details.component.html',
  styleUrls: ['./competition-details.component.css']
})
export class CompetitionDetailsComponent implements OnInit {

  competition: Competition = new Competition();

  constructor(private competitionService:CompetitionsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.competitionService.fetchDetails(params.get("id"))
        .subscribe((resp:any)=>{
          this.competition.id = resp['id'];
          this.competition.name = resp['name'];
          this.competition.matches = resp['matches'];
          this.competition.numberOfMatchesInFirstRound = resp['numberOfMatchesInFirstRound'];
          this.competition.numberOfPlayers = resp['numberOfPlayers'];
          this.competition.numberOfRounds =  resp['numberOfRounds'];
        });
    });
  }

  range(n): number[] {
    return Array.from({length: n}, (value, key) => key + 1);
  }
}
