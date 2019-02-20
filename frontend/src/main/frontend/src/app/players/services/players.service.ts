import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Player } from "../player";

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  constructor(private http: HttpClient) { }

  fetchList(): Observable<Player[]> {
    return this.http.get<Player[]>('api/players');
  }

  fetchListByMatchId(id: string): Observable<{ [key:string]:Player; }> {
    return this.http.get<{ [key:string]:Player; }> ('api/players/match/' + id);
  }

  fetchListByCompetitionId(id: string): Observable<{ [key:string]:Player; }> {
    return this.http.get<{ [key:string]:Player; }>('api/players/competition/' + id);
  }

  fetchDetails(id: string): Observable<Player> {
    return this.http.get<Player>('api/players/' + id);
  }

  add(playerForm) {
    return this.http.post('api/players/add', playerForm);
  }
}
