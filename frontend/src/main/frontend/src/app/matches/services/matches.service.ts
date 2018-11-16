import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  constructor(private http: HttpClient) { }

  fetchList(): Observable<any> {
    return this.http.get('api/matches');
  }

  fetchDetails(id: string): Observable<any> {
    return this.http.get('api/matches/' + id);
  }
}