import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Match } from "../match";

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  constructor(private http: HttpClient) {
  }

  fetchList(): Observable<Match[]> {
    return this.http.get<Match[]>('api/matches');
  }

  fetchListByCompetitionId(id: string): Observable<{ [key: string]: Match; }> {
    return this.http.get<{ [key: string]: Match; }>('api/matches/competition/' + id);
  }

  fetchDetails(id: string): Observable<Match> {
    return this.http.get<Match>('api/matches/' + id);
  }

  edit(id: string, matchForm) {
    return this.http.post('api/matches/edit/' + id, matchForm);
  }

}
