import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompetitionsListComponent } from "./components/competitions-list/competitions-list.component";
import { CompetitionsDetailsComponent } from "./components/competitions-details/competitions-details.component";
import { CompetitionsAddComponent } from "./components/competitions-add/competitions-add.component";
import { AuthGuard } from "../guards/auth.guard";

const routes: Routes = [
  {path: 'competitions', component: CompetitionsListComponent, canActivate: [AuthGuard]},
  {path: 'competitions/details/:id', component: CompetitionsDetailsComponent, canActivate: [AuthGuard]},
  {path: 'competitions/add', component: CompetitionsAddComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompetitionsRoutingModule {
}
