import { Component, OnInit } from '@angular/core';

import { MatchesService } from "../../services/matches.service";

@Component({
  selector: 'app-matches-list',
  templateUrl: './matches-list.component.html',
  styleUrls: ['./matches-list.component.css']
})
export class MatchesListComponent implements OnInit {

  matches: any[];

  constructor(private matchesService:MatchesService) { }

  ngOnInit() {
    this.matchesService.fetchList()
      .subscribe((resp:any)=>{
        this.matches = resp;
      });
  }

}
