import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { BsModalRef, BsModalService } from "ngx-bootstrap";
import { forkJoin } from "rxjs";

import { Match } from "../../match";
import { MatchesService } from "../../services/matches.service";
import { PlayersService } from "../../../players/services/players.service";

@Component({
  selector: 'app-matches-details',
  templateUrl: './matches-details.component.html',
  styleUrls: ['./matches-details.component.css']
})
export class MatchesDetailsComponent implements OnInit {

  @ViewChild('template', { static: true }) template

  modalRef: BsModalRef;
  match: Match = new Match();

  constructor(private route: ActivatedRoute,
              private matchesService:MatchesService,
              private playersService:PlayersService,
              private modalService:BsModalService) { }

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
          this.modalRef = this.modalService.show(this.template, {});
        });
    });
  }

  ngOnDestroy() {
    this.modalRef.hide();
    this.modalRef = null;
  }

  getData(id:string) {

    let matchRequest = this.matchesService.fetchDetails(id);
    let playersRequest = this.playersService.fetchListByMatchId(id);
    return forkJoin([matchRequest, playersRequest]);
  }

}
