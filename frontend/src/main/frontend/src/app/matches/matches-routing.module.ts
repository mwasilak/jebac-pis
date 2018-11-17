import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatchesListComponent } from "./components/matches-list/matches-list.component";
import { MatchesDetailsComponent } from "./components/matches-details/matches-details.component";
import { MatchesEditComponent } from "./components/matches-edit/matches-edit.component";

const routes: Routes = [
  {path: '', component: MatchesListComponent},
  {path: 'details/:id', component: MatchesDetailsComponent},
  {path: 'edit/:id', component: MatchesEditComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MatchesRoutingModule { }
