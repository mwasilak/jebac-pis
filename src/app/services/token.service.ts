import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  public storeToken(token: string) {
    sessionStorage.removeItem("token");
    sessionStorage.setItem("token", token);
  }

  public retrieveToken(): string {
    return sessionStorage.getItem("token");
  }

  public storeUsername(username: string) {
    sessionStorage.removeItem("username");
    sessionStorage.setItem("username", username);
  }

  public retrieveUsername(): string {
    return sessionStorage.getItem("username");
  }
}
