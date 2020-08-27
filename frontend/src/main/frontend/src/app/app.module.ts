import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PlayersModule } from "./players/players.module";
import { MatchesModule } from "./matches/matches.module";
import { CompetitionModule } from "./competition/competition.module";
import { LoginComponent } from './components/login/login.component';
import { AuthService } from "./services/auth.service";
import { XhrInterceptor } from "./interceptors/xhr-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    PlayersModule,
    MatchesModule,
    CompetitionModule,
    FormsModule,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent],
  providers: [
    AuthService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }
  ]
})
export class AppModule { }



