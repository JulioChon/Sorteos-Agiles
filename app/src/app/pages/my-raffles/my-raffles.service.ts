import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { RaffleDTO } from './my-raffles.types';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class MyRafflesService {
  constructor(private readonly http: HttpClient) {}

  getMyRaffles(): Observable<RaffleDTO[]> {
    return this.http.get<RaffleDTO[]>(`${environment.api}/sorteos/admin/${1}`); //TODO: get user id from auth service
  }

  deleteRaffle(id: number): Observable<any> {
    return this.http
      .delete(`${environment.api}/sorteos/${id}`, { responseType: 'text' })
      .pipe(
        map((response) => {
          return id;
        })
      );
  }
}
