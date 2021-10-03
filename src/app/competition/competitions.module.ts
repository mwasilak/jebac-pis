import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompetitionsRoutingModule } from './competitions-routing.module';
import { CompetitionsListComponent } from "./components/competitions-list/competitions-list.component";
import { CompetitionsDetailsComponent } from "./components/competitions-details/competitions-details.component";
import { BracketMatchComponent } from "./components/bracket-match/bracket-match.component";
import { CompetitionsAddComponent } from './components/competitions-add/competitions-add.component';
import { PlayersModule } from "../players/players.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    CompetitionsRoutingModule,
    PlayersModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    CompetitionsListComponent,
    CompetitionsDetailsComponent,
    BracketMatchComponent,
    CompetitionsAddComponent
  ]
})
export class CompetitionsModule {
}
