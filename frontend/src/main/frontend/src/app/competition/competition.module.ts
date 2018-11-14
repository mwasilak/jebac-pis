import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompetitionRoutingModule } from './competition-routing.module';
import { CompetitionListComponent } from "./components/competition-list/competition-list.component";
import { CompetitionDetailsComponent } from "./components/competition-details/competition-details.component";
import { BracketMatchComponent } from "./components/bracket-match/bracket-match.component";
import { CompetitionAddComponent } from './components/competition-add/competition-add.component';
import { PlayersModule } from "../players/players.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    CompetitionRoutingModule,
    PlayersModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    CompetitionListComponent,
    CompetitionDetailsComponent,
    BracketMatchComponent,
    CompetitionAddComponent
  ]
})
export class CompetitionModule { }
