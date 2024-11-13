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
