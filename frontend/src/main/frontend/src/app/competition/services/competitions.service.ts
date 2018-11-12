import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class CompetitionsService {

  constructor(private http: HttpClient) { }

  fetchList(): Observable<any> {
    return this.http.get('api/competitions');
  }

  fetchDetails(id: string): Observable<any> {
    return this.http.get('api/competitions/' + id);
  }

}
