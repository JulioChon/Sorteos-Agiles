export interface RaffleDTO {
  id: number;
  name: string;
  status: string;
  raffleDate: Date;
  startDate: Date;
  endDate: Date;
  image: string;
  minRange: number;
  maxRange: number;
}

export interface SorteoDTO {
  estado: string;
  fechaFinVenta: string;
  fechaInicioVenta: string;
  fechaSorteo: string;
  id: number;
  imagenSorteo: string;
  nombre: string;
  rangoMax: number;
  rangoMin: number;
}

export interface BoletoDTO {
  id: number;
  numeroBoleto: number;
  estado: BoletoEstado;
  precio: number;
  fechaLimApart: string;
}

export enum BoletoEstado {
  LIBRE = 'LIBRE',
  VENDIDO = 'VENDIDO',
  APARTADO = 'APARTADO'
}

export interface RaffleTicketDTO {
  id: number;
  ticketNumber: number;
  status: RaffleTicketStatus;
  price: number;
  reservationLimitDate: Date;
}

export enum RaffleTicketStatus {
  FREE = 'FREE',
  SOLD = 'SOLD',
  RESERVED = 'RESERVED'
}