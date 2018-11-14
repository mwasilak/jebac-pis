import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {CompetitionDetailsComponent} from "./components/competition-details/competition-details.component";
import {CompetitionAddComponent} from "./components/competition-add/competition-add.component";

const routes: Routes = [
  { path: '', component: CompetitionListComponent },
  { path: 'details/:id', component: CompetitionDetailsComponent},
  { path: 'add', component: CompetitionAddComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionRoutingModule { }
