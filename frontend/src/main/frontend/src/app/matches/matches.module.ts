import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatchesRoutingModule } from './matches-routing.module';
import { MatchesDetailsComponent } from './components/matches-details/matches-details.component';
import { MatchesListComponent } from './components/matches-list/matches-list.component';
import { MatchesEditComponent } from './components/matches-edit/matches-edit.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatchesRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [MatchesDetailsComponent, MatchesListComponent, MatchesEditComponent]
})
export class MatchesModule { }
