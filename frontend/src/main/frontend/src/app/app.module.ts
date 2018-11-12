import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { CompetitionListComponent } from './components/competition-list/competition-list.component';
import { PlayersComponent } from './components/players/players.component';
import { MatchesComponent } from './components/matches/matches.component';
import { CompetitionDetailsComponent } from './components/competition-details/competition-details.component';
import { BracketMatchComponent } from './components/bracket-match/bracket-match.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CompetitionListComponent,
    PlayersComponent,
    MatchesComponent,
    CompetitionDetailsComponent,
    BracketMatchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
