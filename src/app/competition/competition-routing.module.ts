import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompetitionListComponent } from "./components/competition-list/competition-list.component";
import { CompetitionDetailsComponent } from "./components/competition-details/competition-details.component";
import { CompetitionAddComponent } from "./components/competition-add/competition-add.component";
import { AuthGuard } from "../guards/auth.guard";

const routes: Routes = [
  {path: 'competitions', component: CompetitionListComponent, canActivate: [AuthGuard]},
  {path: 'competitions/details/:id', component: CompetitionDetailsComponent, canActivate: [AuthGuard]},
  {path: 'competitions/add', component: CompetitionAddComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionRoutingModule {
}
