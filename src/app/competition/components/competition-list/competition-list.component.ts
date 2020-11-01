import { Component, OnInit } from '@angular/core';
import {CompetitionsService} from "../../services/competitions.service";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  competitions: any[];

  constructor(private competitionService:CompetitionsService) {
  }

  ngOnInit() {
    this.competitionService.fetchList()
      .subscribe((resp:any)=>{
        this.competitions = resp;
      });
  }

}
