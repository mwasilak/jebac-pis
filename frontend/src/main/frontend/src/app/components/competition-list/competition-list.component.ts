import { Component, OnInit } from '@angular/core';
import {CompetitionsService} from "../../services/competitions.service";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  competitions: any[];

  config: any[] = [
    {key: 'name'}
  ]

  constructor(private competitionService:CompetitionsService) {
    this.competitionService.fetchList()
      .subscribe((resp:any)=>{
        this.competitions = resp;
      });
  }

  ngOnInit() {
  }

}
