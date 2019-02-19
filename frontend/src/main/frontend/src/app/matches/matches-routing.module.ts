import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatchesListComponent } from "./components/matches-list/matches-list.component";
import { MatchesEditComponent } from "./components/matches-edit/matches-edit.component";
import { MatchesDetailsComponent } from "./components/matches-details/matches-details.component";

const routes: Routes = [
  {path: 'matches', component: MatchesListComponent},
  {path: 'matches/details/:id', component: MatchesDetailsComponent, outlet: 'modal'},
  {path: 'matches/edit/:id', component: MatchesEditComponent, outlet: 'modal'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MatchesRoutingModule { }
