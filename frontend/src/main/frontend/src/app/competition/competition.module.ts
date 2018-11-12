import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompetitionRoutingModule } from './competition-routing.module';
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {CompetitionDetailsComponent} from "./components/competition-details/competition-details.component";
import {BracketMatchComponent} from "./components/bracket-match/bracket-match.component";

@NgModule({
  imports: [
    CommonModule,
    CompetitionRoutingModule
  ],
  declarations: [
    CompetitionListComponent,
    CompetitionDetailsComponent,
    BracketMatchComponent
  ]
})
export class CompetitionModule { }
