import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  constructor(private http: HttpClient) { }

  fetchList(): Observable<any> {
    return this.http.get('api/players');
  }

  fetchListByMatchId(id: string): Observable<any> {
    return this.http.get('api/players/match/' + id);
  }

  fetchListByCompetitionId(id: string): Observable<any> {
    return this.http.get('api/players/competition/' + id);
  }

  fetchDetails(id: string): Observable<any> {
    return this.http.get('api/players/' + id);
  }

  add(playerForm) {
    return this.http.post('api/players/add', playerForm);
  }
}
