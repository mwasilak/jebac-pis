import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayersListComponent } from "./components/players-list/players-list.component";
import { PlayersDetailsComponent } from "./components/players-details/players-details.component";
import { PlayersAddComponent } from "./components/players-add/players-add.component";
import { AuthGuard } from "../guards/auth.guard";


const routes: Routes = [
  { path: 'players', component: PlayersListComponent, canActivate: [AuthGuard] },
  { path: 'players/details/:id', component: PlayersDetailsComponent, canActivate: [AuthGuard] },
  { path: 'players/add', component: PlayersAddComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlayersRoutingModule { }
