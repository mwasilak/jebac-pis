import { Component, OnInit } from '@angular/core';

import { MatchesService } from "../../services/matches.service";
import { Match } from "../../match";

@Component({
  selector: 'app-matches-list',
  templateUrl: './matches-list.component.html',
  styleUrls: ['./matches-list.component.css']
})
export class MatchesListComponent implements OnInit {

  matches: Array<Match>;

  constructor(private matchesService: MatchesService) {
  }

  ngOnInit() {
    this.matchesService.fetchList()
      .subscribe((resp: Array<Match>) => {
        this.matches = resp;
      });
  }

}
