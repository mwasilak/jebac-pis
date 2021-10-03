import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { forkJoin } from "rxjs";

import { CompetitionsService } from "../../services/competitions.service";
import { MatchesService } from "../../../matches/services/matches.service";
import { PlayersService } from "../../../players/services/players.service";
import { Competition } from "../../competition";
import { Player } from "../../../players/player";
import { Match } from "../../../matches/match";

@Component({
  selector: 'app-competitions-details',
  templateUrl: './competitions-details.component.html',
  styleUrls: ['./competitions-details.component.css']
})
export class CompetitionsDetailsComponent implements OnInit {

  competition: Competition = new Competition();
  matches: { [key: string]: Match; };
  players: { [key: string]: Player; };

  constructor(private competitionsService: CompetitionsService,
              private matchesService: MatchesService,
              private playersService: PlayersService,
              private router: Router,
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
          this.competition.numberOfRounds = competitionResponse['numberOfRounds'];

          this.matches = matchesResponse;

          this.players = playersResponse;
        });
    });
  }

  getData(id: string) {

    let competitionRequest = this.competitionsService.fetchDetails(id);
    let matchesRequest = this.matchesService.fetchListByCompetitionId(id);
    let playersRequest = this.playersService.fetchListByCompetitionId(id);
    return forkJoin([competitionRequest, matchesRequest, playersRequest]);
  }

  range(n): number[] {
    return Array.from({length: n}, (value, key) => key + 1);
  }

  getMatchByPosition(round: number, position: number): Match {

    let match = this.matches['[' + round + '/' + position + ']'];
    if (match != undefined) {
      match.player1 = this.players[match.player1Id];
      match.player2 = this.players[match.player2Id];
    }
    return match;
  }

}
