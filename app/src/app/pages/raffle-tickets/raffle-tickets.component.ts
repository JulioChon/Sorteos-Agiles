import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { RaffleTicketsService } from './raffle-tickets.service';
import { RaffleDTO } from './raffle-tickets.types';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-raffle-tickets',
  standalone: true,
  imports: [CommonModule, FooterComponent, HeaderComponent],
  templateUrl: './raffle-tickets.component.html',
  styleUrl: './raffle-tickets.component.scss',
  providers: [RaffleTicketsService],
})
export class RaffleTicketsComponent implements OnInit {
  raffle: RaffleDTO;

  constructor(private readonly raffleTicketsService: RaffleTicketsService,
    private readonly activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const raffleId = this.getRaffleIdFromRoute();
    this.loadRaffle(raffleId);
  }

  loadRaffle(id: number): void {
    this.raffleTicketsService.findRaffleById(id).subscribe((raffle: RaffleDTO) => {
      this.raffle = raffle;
    });
  }

  getRaffleIdFromRoute(): number {
    return this.activatedRoute.snapshot.params['id'];
  }

  createRange(number: number): number[] {
    return Array.from({ length: number }, (_, i) => i);
  }

  getTicketsArray(minRange: number, maxRange: number): number[] {
    return this.createRange(maxRange - minRange + 1).map((_, i) => i + minRange);
  }

  isTicketAvailable(ticket: number): boolean {
    // Lógica para verificar si el boleto está disponible
    return true; // Cambia esto según tu lógica
  }

  isTicketReserved(ticket: number): boolean {
    // Lógica para verificar si el boleto está apartado
    return false; // Cambia esto según tu lógica
  }

  isTicketSold(ticket: number): boolean {
    // Lógica para verificar si el boleto está vendido
    return false; // Cambia esto según tu lógica
  }
}
