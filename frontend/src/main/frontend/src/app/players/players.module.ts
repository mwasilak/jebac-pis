import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlayersRoutingModule } from './players-routing.module';
import { PlayersListComponent } from './components/players-list/players-list.component';

@NgModule({
  imports: [
    CommonModule,
    PlayersRoutingModule
  ],
  declarations: [PlayersListComponent]
})
export class PlayersModule { }
