import { RaffleStatus } from "../../shared/types/raffle-status.enum";

export interface Raffle {
    nombre: string;
    imagenSorteo: string;
    rangoMax: number;
    rangoMin: number;
    fechaSorteo: string;
    fechaInicioVenta: string;
    fechaFinVenta: string;
    estado: RaffleStatus;
    precio: number;
}
