import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

import { Competition } from "../competition";

@Injectable({
  providedIn: 'root'
})
export class CompetitionsService {

  constructor(private http: HttpClient) { }

  fetchList(): Observable<Competition[]> {
    return this.http.get<Competition[]>('api/competitions');
  }

  fetchDetails(id: string): Observable<Competition> {
    return this.http.get<Competition>('api/competitions/' + id);
  }

  fetchDetailsByMatchId(id: string): Observable<Competition> {
    return this.http.get<Competition>('api/competitions/match/' + id);
  }

  add(competitionForm) {
    return this.http.post('api/competitions/add', competitionForm);
  }

}
