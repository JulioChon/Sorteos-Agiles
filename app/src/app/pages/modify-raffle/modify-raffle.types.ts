import { RaffleStatus } from "../../shared/types/raffle-status.enum";

export interface Params {
    id: string;
}

export interface Raffle {
    id: number,
    title: string,
    raffleImage: string,
    maxRange: number,
    minRange: number,
    startDate: Date,
    endDate: Date,
    raffleDate: Date,
    status: RaffleStatus
    price: number
}

export interface Sorteo {
    id: number;
    nombre: string;
    imagenSorteo: string;
    rangoMax: number;
    rangoMin: number;
    fechaInicioVenta: string;
    fechaFinVenta: string;
    fechaSorteo: string;
    estado: RaffleStatus;
    precio: number;
}
