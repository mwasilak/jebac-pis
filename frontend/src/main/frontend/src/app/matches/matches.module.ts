import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatchesRoutingModule } from './matches-routing.module';
import { MatchesDetailsComponent } from './components/matches-details/matches-details.component';
import { MatchesListComponent } from './components/matches-list/matches-list.component';

@NgModule({
  imports: [
    CommonModule,
    MatchesRoutingModule
  ],
  declarations: [MatchesDetailsComponent, MatchesListComponent]
})
export class MatchesModule { }
