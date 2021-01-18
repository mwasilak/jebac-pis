import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatchesListComponent } from "./components/matches-list/matches-list.component";
import { MatchesEditComponent } from "./components/matches-edit/matches-edit.component";
import { MatchesDetailsComponent } from "./components/matches-details/matches-details.component";
import { AuthGuard } from "../guards/auth.guard";

const routes: Routes = [
  {path: 'matches', component: MatchesListComponent, canActivate: [AuthGuard]},
  {path: 'matches/details/:id', component: MatchesDetailsComponent, outlet: 'modal', canActivate: [AuthGuard]},
  {path: 'matches/edit/:id', component: MatchesEditComponent, outlet: 'modal', canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MatchesRoutingModule {
}
