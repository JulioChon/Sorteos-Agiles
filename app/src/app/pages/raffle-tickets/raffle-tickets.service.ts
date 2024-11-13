import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RaffleDTO, SorteoDTO } from './raffle-tickets.types';
import { map, Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class RaffleTicketsService {
  constructor(private readonly http: HttpClient) {}

  findRaffleById(id: number): Observable<RaffleDTO> {
    return this.http.get<SorteoDTO>(`${environment.api}/sorteos/${id}`).pipe(
      map((sorteo: SorteoDTO) => {
        return {
          id: sorteo.id,
          name: sorteo.nombre,
          status: sorteo.estado,
          raffleDate: new Date(sorteo.fechaSorteo),
          startDate: new Date(sorteo.fechaInicioVenta),
          endDate: new Date(sorteo.fechaFinVenta),
          image: sorteo.imagenSorteo,
          minRange: sorteo.rangoMin,
          maxRange: sorteo.rangoMax,
        };
      })
    );
  }
}
