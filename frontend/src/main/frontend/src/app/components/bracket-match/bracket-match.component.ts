import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-bracket-match',
  templateUrl: './bracket-match.component.html',
  styleUrls: ['./bracket-match.component.css']
})
export class BracketMatchComponent implements OnInit {

  @Input() match: any[];
  @Input() middleBar: boolean;
  constructor() { }

  ngOnInit() {
  }

}
