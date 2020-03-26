import {Component, Input, OnInit} from '@angular/core';
import {Match} from "../../../matches/match";

@Component({
  selector: 'app-bracket-match',
  templateUrl: './bracket-match.component.html',
  styleUrls: ['./bracket-match.component.css']
})
export class BracketMatchComponent implements OnInit {

  @Input() match: Match;
  @Input() middleBar: boolean;

  constructor() { }

  ngOnInit() {
  }

}
