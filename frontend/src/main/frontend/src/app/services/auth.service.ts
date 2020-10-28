import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import {TokenService} from "./token.service";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  authenticate(credentials) : Observable<any> {
    return this.http.post<any>('authenticate', credentials, httpOptions)
      .pipe(
        map(userData => {
          this.tokenService.storeUsername(credentials.username);
          let tokenStr = "Bearer " + userData.authToken;
          this.tokenService.storeToken(userData.authToken);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
  }


}
