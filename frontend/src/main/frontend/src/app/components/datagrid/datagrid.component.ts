import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-datagrid',
  templateUrl: './datagrid.component.html',
  styleUrls: ['./datagrid.component.css']
})
export class DatagridComponent implements OnInit {

  @Input() data: any[];
  @Input() config: any[];
  constructor() { }

  ngOnInit() {
  }

}
