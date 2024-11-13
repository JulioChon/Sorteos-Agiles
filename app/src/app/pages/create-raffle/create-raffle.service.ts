import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Raffle } from './create-raffle.types';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class CreateRaffleService {

  constructor(
    private readonly http: HttpClient
  ) { }

  createRaffle(raffle: Raffle): Observable<Raffle> {
    return this.http.post<Raffle>(`${environment.api}/sorteos/${1}`, raffle);
  }

}
