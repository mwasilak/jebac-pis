import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { forkJoin } from "rxjs";

import { MatchesService } from "../../services/matches.service";
import { Match } from "../../match";
import { PlayersService } from "../../../players/services/players.service";

@Component({
  selector: 'app-matches-details',
  templateUrl: './matches-details.component.html',
  styleUrls: ['./matches-details.component.css']
})
export class MatchesDetailsComponent implements OnInit {

  match: Match = new Match();

  constructor(private matchesService:MatchesService,
              private playersService:PlayersService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {

      this.getData(params.get("id"))
        .subscribe(responseList => {
          let matchResponse = responseList[0];
          let playersResponse = responseList[1];

          this.match.id = matchResponse['id'];
          this.match.name = matchResponse['name'];
          this.match.player1 = playersResponse[matchResponse['player1Id']];
          this.match.player2 = playersResponse[matchResponse['player2Id']];
          this.match.games = matchResponse['games'];
          this.match.bracketPosition = matchResponse['bracketPosition'];
          this.match.resultRegistrationTime = matchResponse['resultRegistrationTime'];
          this.match.initialize();
        });
    });
  }

  getData(id:string) {

    let matchRequest = this.matchesService.fetchDetails(id);
    let playersRequest = this.playersService.fetchListByMatchId(id);
    return forkJoin([matchRequest, playersRequest]);
  }

}
