import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatchesListComponent } from "./components/matches-list/matches-list.component";
import { MatchesDetailsComponent } from "./components/matches-details/matches-details.component";

const routes: Routes = [
  {path: '', component: MatchesListComponent},
  {path: 'details/:id', component: MatchesDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MatchesRoutingModule { }
