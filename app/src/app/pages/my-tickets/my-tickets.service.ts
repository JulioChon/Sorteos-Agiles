import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BoletoDTO } from './my-tickets.types';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class MyTicketsService {

  constructor(private readonly http: HttpClient) { }

  findMyTickets(clientId: number) {
    return this.http.get<BoletoDTO>(`${environment.api}/boletos/cliente/${clientId}`);
  }
}
