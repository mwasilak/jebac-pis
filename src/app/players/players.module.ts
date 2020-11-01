import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlayersRoutingModule } from './players-routing.module';
import { PlayersListComponent } from './components/players-list/players-list.component';
import { PlayersDetailsComponent } from './components/players-details/players-details.component';
import { PlayersAddComponent } from './components/players-add/players-add.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    PlayersRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [PlayersListComponent, PlayersDetailsComponent, PlayersAddComponent]
})
export class PlayersModule { }
