import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlayersRoutingModule } from './players-routing.module';
import { PlayersListComponent } from './components/players-list/players-list.component';
import { PlayersDetailsComponent } from './components/players-details/players-details.component';

@NgModule({
  imports: [
    CommonModule,
    PlayersRoutingModule
  ],
  declarations: [PlayersListComponent, PlayersDetailsComponent]
})
export class PlayersModule { }
