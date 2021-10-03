import { Component, OnInit } from '@angular/core';
import { CompetitionsService } from "../../services/competitions.service";
import { Competition } from "../../competition";

@Component({
  selector: 'app-competitions-list',
  templateUrl: './competitions-list.component.html',
  styleUrls: ['./competitions-list.component.css']
})
export class CompetitionsListComponent implements OnInit {

  competitions: Array<Competition>;

  constructor(private competitionsService: CompetitionsService) {
  }

  ngOnInit() {
    this.competitionsService.fetchList()
      .subscribe((resp: Array<Competition>) => {
        this.competitions = resp;
      });
  }

}
