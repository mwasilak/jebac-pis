import { Component, OnInit } from '@angular/core';
import {CompetitionsService} from "../../services/competitions.service";

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent implements OnInit {

  displayedColumns = ['position', 'name'];
  competitions: any[];

  constructor(private competitionService:CompetitionsService) {

    this.competitionService.fetchList()
      .subscribe((resp:any)=>{
        this.competitions = resp;
      });
  }

  ngOnInit() {
  }

}
