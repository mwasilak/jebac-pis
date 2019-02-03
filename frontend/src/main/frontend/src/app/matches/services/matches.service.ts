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

  fetchListByCompetitionId(id: string): Observable<any> {
    return this.http.get('api/matches/competition/' + id);
  }

  fetchDetails(id: string): Observable<any> {
    return this.http.get('api/matches/' + id);
  }

  edit(id: string, matchForm) {
    return this.http.post('api/matches/edit/' + id, matchForm);
  }

}
