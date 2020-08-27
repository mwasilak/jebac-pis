import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private authService: AuthService, private router: Router) {
    this.authService.authenticate(undefined);
  }
  logout() {
    this.authService.logout().pipe(
      finalize(() => {
        this.authService.authenticated = false;
        this.router.navigateByUrl('/login');
    })).subscribe();
  }

}
