import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private baseUrl = 'http://localhost:8080/api/players';
  constructor(private http: HttpClient) { }

  getPlayer(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPlayer(player: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, player);
  }

  updatePlayer(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePlayer(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getPlayersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getPlayersByName(playerfullName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/playerName/${playerfullName}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }
}
