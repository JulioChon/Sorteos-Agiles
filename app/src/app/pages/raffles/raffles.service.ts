import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BoletoDTO, RaffleDTO, SorteoDTO, TicketDTO } from './raffles.types';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class RafflesService {

  constructor(
    private readonly http: HttpClient
  ) { }

  findRaffles(): Observable<RaffleDTO[]>{
    return this.http.get<SorteoDTO[]>(`${environment.api}/sorteos`).pipe(
      map((sorteos: SorteoDTO[]) => {
        return sorteos.map((sorteo: SorteoDTO) => {
          return {
            id: sorteo.id,
            name: sorteo.nombre,
            status: sorteo.estado,
            raffleDate: new Date(sorteo.fechaSorteo),
            startDate: new Date(sorteo.fechaInicioVenta),
            endDate: new Date(sorteo.fechaInicioVenta),
            image: sorteo.imagenSorteo,
            minRange: sorteo.rangoMin,
            maxRange: sorteo.rangoMax
          };
        });
      })
    );
  }
}
