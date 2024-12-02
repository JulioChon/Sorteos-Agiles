export interface BoletoDTO {
  id: number;
  numeroBoleto: number;
  estado: BoletoEstado;
  precio: number;
  idSorteo: any;
}

export enum BoletoEstado {
  LIBRE = 'LIBRE',
  VENDIDO = 'VENDIDO',
  APARTADO = 'APARTADO',
}
