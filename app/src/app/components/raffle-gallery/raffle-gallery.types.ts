import { RaffleStatus } from "../../shared/types/raffle-status.enum";

export interface RaffleDTO {
    id: number;
    nombre: string;
    imagenSorteo: string;
    estado: RaffleStatus;
}

export interface ButtonAction {
    label: string;
    cssClass: string;
    action: () => void;
}