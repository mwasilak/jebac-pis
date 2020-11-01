import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {CompetitionDetailsComponent} from "./components/competition-details/competition-details.component";
import {CompetitionAddComponent} from "./components/competition-add/competition-add.component";

const routes: Routes = [
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'competitions/details/:id', component: CompetitionDetailsComponent},
  { path: 'competitions/add', component: CompetitionAddComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionRoutingModule { }
