import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { RaffleDTO } from './raffle-gallery.types';
import { RaffleStatus } from '../../shared/types/raffle-status.enum';

@Component({
  selector: 'app-raffle-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './raffle-gallery.component.html',
  styleUrl: './raffle-gallery.component.scss'
})
export class RaffleGalleryComponent implements OnInit {

  @Input() raffles: RaffleDTO[];

  constructor() { }

  ngOnInit(): void {
  }

  colorClassByStatus(status: RaffleStatus): 'success' | 'secondary' | 'danger' | 'dark' {
    switch (status) {
      case RaffleStatus.ACTIVO:
        return 'success';
      case RaffleStatus.INACTIVO:
        return 'secondary';
      case RaffleStatus.CANCELADO:
        return 'danger';
      default:
        return 'dark';
    }
  }
}
