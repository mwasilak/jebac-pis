import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PlayersListComponent} from "./components/players-list/players-list.component";


const routes: Routes = [
  { path: '', component: PlayersListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlayersRoutingModule { }
