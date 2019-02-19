import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PlayersModule } from "./players/players.module";
import { MatchesModule } from "./matches/matches.module";
import { CompetitionModule } from "./competition/competition.module";



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    PlayersModule,
    MatchesModule,
    CompetitionModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }



