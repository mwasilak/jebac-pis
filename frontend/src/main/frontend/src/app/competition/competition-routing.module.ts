import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {CompetitionDetailsComponent} from "./components/competition-details/competition-details.component";

const routes: Routes = [
  { path: '', component: CompetitionListComponent },
  { path: ':id', component: CompetitionDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionRoutingModule { }
