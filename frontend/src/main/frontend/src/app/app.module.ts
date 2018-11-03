import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { CompetitionsComponent } from './components/competitions/competitions.component';
import { PlayersComponent } from './components/players/players.component';
import { MatchesComponent } from './components/matches/matches.component';
import { CompetitionDetailsComponent } from './components/competition-details/competition-details.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CompetitionsComponent,
    PlayersComponent,
    MatchesComponent,
    CompetitionDetailsComponent
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
