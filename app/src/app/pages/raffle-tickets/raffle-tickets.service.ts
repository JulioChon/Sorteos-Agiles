import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BoletoDTO, BoletoEstado, RaffleDTO, RaffleTicketDTO, RaffleTicketStatus, SorteoDTO } from './raffle-tickets.types';
import { map, Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { RaffleStatus } from '@shared/types/raffle-status.enum';

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

  findTicketsByRaffleId(id: number): Observable<RaffleTicketDTO[]> {
    return this.http.get<BoletoDTO[]>(`${environment.api}/boletos/sorteo/${id}`).pipe(
      map((boletos: BoletoDTO[]) =>
        boletos.map((boleto) => ({
          id: boleto.id,
          ticketNumber: boleto.numeroBoleto,
          status: (() => {
            switch (boleto.estado) {
              case BoletoEstado.LIBRE:
                return RaffleTicketStatus.FREE;
              case BoletoEstado.VENDIDO:
                return RaffleTicketStatus.SOLD;
              case BoletoEstado.APARTADO:
                return RaffleTicketStatus.RESERVED;
              default:
                throw new Error(`Estado desconocido: ${boleto.estado}`);
            }
          })(),
          price: boleto.precio,
          reservationLimitDate: new Date(boleto.fechaLimApart),
        }))
      )
    );
  }

}
