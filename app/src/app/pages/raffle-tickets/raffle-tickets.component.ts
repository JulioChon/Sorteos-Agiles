import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { RaffleTicketsService } from './raffle-tickets.service';
import { RaffleDTO, RaffleTicketDTO } from './raffle-tickets.types';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AlertService } from '@shared/services/alert.service';

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
  tickets: RaffleTicketDTO[];

  constructor(private readonly raffleTicketsService: RaffleTicketsService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly alertService: AlertService
  ) {}

  ngOnInit(): void {
    const raffleId = this.getRaffleIdFromRoute();
    this.loadRaffle(raffleId);
  }

  loadRaffle(id: number): void {
    this.raffleTicketsService.findRaffleById(id).subscribe({
      next: (raffle: RaffleDTO) => {
        this.raffle = raffle;
        this.loadTickets();
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al obtener el sorteo', 'Error');
      }
    });
  }

  loadTickets(): void {
    this.raffleTicketsService.findTicketsByRaffleId(this.raffle.id).subscribe({
      next: (tickets: RaffleTicketDTO[]) => {
        this.tickets = tickets;
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al obtener los boletos', 'Error');
      }
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
