import { Component, OnInit } from '@angular/core';
import { CompetitionsService } from "../../services/competitions.service";
import { Competition } from "../../competition";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  competitions: Array<Competition>;

  constructor(private competitionService: CompetitionsService) {
  }

  ngOnInit() {
    this.competitionService.fetchList()
      .subscribe((resp: Array<Competition>) => {
        this.competitions = resp;
      });
  }

}
