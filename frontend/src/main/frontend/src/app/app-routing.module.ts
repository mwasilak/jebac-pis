import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {CompetitionsComponent} from "./components/competitions/competitions.component";
import {PlayersComponent} from "./components/players/players.component";
import {MatchesComponent} from "./components/matches/matches.component";
import {HomeComponent} from "./components/home/home.component";
import {CompetitionDetailsComponent} from "./components/competition-details/competition-details.component";


const appRoutes: Routes = [
  { path: 'competitions', component: CompetitionsComponent },
  { path: 'competitions/:id', component: CompetitionDetailsComponent},
  { path: 'players', component: PlayersComponent },
  { path: 'matches', component: MatchesComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
  
}
