import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { BsModalRef, BsModalService } from "ngx-bootstrap";
import { forkJoin } from "rxjs";

import { Match } from "../../match";
import { MatchesService } from "../../services/matches.service";
import { PlayersService } from "../../../players/services/players.service";
import { Player } from "../../../players/player";

@Component({
  selector: 'app-matches-details',
  templateUrl: './matches-details.component.html',
  styleUrls: ['./matches-details.component.css']
})
export class MatchesDetailsComponent implements OnInit, OnDestroy {

  @ViewChild('template', { static: true }) template

  modalRef: BsModalRef;
  match: Match = new Match();
  player1: Player;
  player2: Player;

  constructor(private route: ActivatedRoute,
              private matchesService:MatchesService,
              private playersService:PlayersService,
              private router: Router,
              private modalService:BsModalService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.getData(params.get("id"))
        .subscribe(responseList => {
          this.match = responseList[0];
          this.player1 = responseList[1][this.match.player1Id];
          this.player2 = responseList[1][this.match.player2Id];

          this.modalRef = this.modalService.show(this.template, {});
          this.modalService.onHide.subscribe((reason: string) => {
            if(reason === "backdrop-click") {
              this.router.navigate(['', {outlets: {modal: null}}]);
            }
          })
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
