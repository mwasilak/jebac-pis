import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Observable, ReplaySubject } from "rxjs";
import { TokenService } from "./token.service";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class AuthService {

  private loginStatus = new ReplaySubject<boolean>(1);

  constructor(private http: HttpClient, private tokenService: TokenService) {
    this.retrieveExistingLoginData();
  }

  authenticate(credentials): Observable<any> {
    return this.http.post<any>('authenticate', credentials, httpOptions)
      .pipe(
        map(userData => this.login(userData, credentials))
      );
  }

  register(user): Observable<any> {
    return this.http.post<any>('register', user);
  }

  isUserLoggedIn(): Observable<boolean> {
    return this.loginStatus.asObservable();
  }

  logout() {
    this.tokenService.removeUsername();
    this.tokenService.removeToken();
    this.loginStatus.next(false);
  }


  private login(userData, credentials) {
    this.tokenService.storeUsername(credentials.username);
    this.tokenService.storeToken(userData.authToken);
    this.loginStatus.next(true);
    return userData;
  }

  private retrieveExistingLoginData() {
    if (this.tokenService.retrieveUsername() != null && this.tokenService.retrieveToken() != null) {
      this.loginStatus.next(true);
    }
  }
}
