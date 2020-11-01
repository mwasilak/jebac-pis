import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayersListComponent } from "./components/players-list/players-list.component";
import { PlayersDetailsComponent } from "./components/players-details/players-details.component";
import { PlayersAddComponent } from "./components/players-add/players-add.component";


const routes: Routes = [
  { path: 'players', component: PlayersListComponent },
  { path: 'players/details/:id', component: PlayersDetailsComponent},
  { path: 'players/add', component: PlayersAddComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlayersRoutingModule { }
