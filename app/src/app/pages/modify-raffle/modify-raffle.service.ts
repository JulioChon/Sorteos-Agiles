import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Raffle, Sorteo } from './modify-raffle.types';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ModifyRaffleService {

  constructor(
    private readonly http: HttpClient
  ) { }

  findRaffleById(id: string): Observable<Raffle> {
    return this.http.get<Sorteo>(`${environment.api}/sorteos/${id}`).pipe(
      map((sorteo: Sorteo) => {
        return {
          id: sorteo.id.toString(),
          title: sorteo.nombre,
          raffleImage: sorteo.imagenSorteo,
          maxRange: sorteo.rangoMax,
          minRange: sorteo.rangoMin,
          startDate: new Date(sorteo.fechaInicioVenta),
          endDate: new Date(sorteo.fechaFinVenta),
          raffleDate: new Date(sorteo.fechaSorteo),
          status: sorteo.estado
        };
      })
    );
  }
}
