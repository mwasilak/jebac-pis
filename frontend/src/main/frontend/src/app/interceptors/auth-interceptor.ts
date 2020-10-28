import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpInterceptor } from '@angular/common/http';
import { TokenService } from "../services/token.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    if (this.tokenService.retrieveUsername() && this.tokenService.retrieveToken()) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.tokenService.retrieveToken()
        }
      })
    }
    return next.handle(req);
  }
}
