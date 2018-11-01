import { Component } from '@angular/core';
import {MatIconRegistry} from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tournament';
  public constructor (public matIconRegistry: MatIconRegistry) {
    //add custom material icons
    matIconRegistry.registerFontClassAlias ('fontawesome','fa');
  }
}
