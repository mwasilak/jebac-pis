import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { forkJoin } from "rxjs";

import { CompetitionsService } from "../../services/competitions.service";
import { MatchesService } from "../../../matches/services/matches.service";
import { PlayersService } from "../../../players/services/players.service";
import { Competition } from "../../competition";

@Component({
  selector: 'app-competition-details',
  templateUrl: './competition-details.component.html',
  styleUrls: ['./competition-details.component.css']
})
export class CompetitionDetailsComponent implements OnInit {

  competition: Competition = new Competition();
  matches: any[];
  players: any[];

  constructor(private competitionService:CompetitionsService,
              private matchesService:MatchesService,
              private playersService:PlayersService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {

      this.getData(params.get("id"))
        .subscribe(responseList => {
          let competitionResponse = responseList[0];
          let matchesResponse = responseList[1];
          let playersResponse = responseList[2];

          this.competition.id = competitionResponse['id'];
          this.competition.name = competitionResponse['name'];
          this.competition.numberOfMatchesInFirstRound = competitionResponse['numberOfMatchesInFirstRound'];
          this.competition.numberOfPlayers = competitionResponse['numberOfPlayers'];
          this.competition.numberOfRounds =  competitionResponse['numberOfRounds'];

          this.matches = matchesResponse;

          this.players = playersResponse;
        });
    });
  }

  getData(id:string) {

    let competitionRequest = this.competitionService.fetchDetails(id);
    let matchesRequest = this.matchesService.fetchListByCompetitionId(id);
    let playersRequest = this.playersService.fetchListByCompetitionId(id);
    return forkJoin([competitionRequest, matchesRequest, playersRequest]);
  }

  range(n): number[] {
    return Array.from({length: n}, (value, key) => key + 1);
  }

  getMatchByPosition(round: number, position: number): any {

    let match = this.matches['['+round+'/'+position+']'];
    match.player1 = this.players[match.player1Id];
    match.player2 = this.players[match.player2Id];
    return match;
  }

}
