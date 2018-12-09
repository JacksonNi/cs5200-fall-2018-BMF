import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  private baseUrl = 'http://localhost:8080/api/clubs';

  constructor(private http: HttpClient) { }

  getClub(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createClub(club: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, club);
  }

  updateClub(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteClub(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getClubsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getClubsByClubName(clubName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/clubName/${clubName}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }
}
