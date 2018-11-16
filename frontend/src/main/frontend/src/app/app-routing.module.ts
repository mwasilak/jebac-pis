import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./components/home/home.component";



const appRoutes: Routes = [
  { path: 'competitions', loadChildren: './competition/competition.module#CompetitionModule'},
  { path: 'players', loadChildren: './players/players.module#PlayersModule'},
  { path: 'matches', loadChildren: './matches/matches.module#MatchesModule' },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
