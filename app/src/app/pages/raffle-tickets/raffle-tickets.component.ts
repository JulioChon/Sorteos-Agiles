import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { RaffleTicketsService } from './raffle-tickets.service';
import { RaffleDTO, RaffleTicketDTO, RaffleTicketStatus } from './raffle-tickets.types';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AlertService } from '@shared/services/alert.service';
import { AuthService } from '@shared/services/auth/auth.service';

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
  selected: RaffleTicketDTO[];
  loading: boolean = false;

  constructor(private readonly raffleTicketsService: RaffleTicketsService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly alertService: AlertService,
    private readonly authService: AuthService
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
    this.selected = [];
    this.raffleTicketsService.findTicketsByRaffleId(this.raffle.id).subscribe({
      next: (tickets: RaffleTicketDTO[]) => {
        this.tickets = tickets;
        this.selected = tickets.filter((ticket) => ticket.status === RaffleTicketStatus.SELECTED);
      },
      error: (error) => {
        console.error(error);
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

  defineTicketStatus(status: RaffleTicketStatus): string {
    switch (status) {
      case RaffleTicketStatus.FREE:
        return 'btn-success';
      case RaffleTicketStatus.RESERVED:
        return 'btn-warning';
      case RaffleTicketStatus.SOLD:
        return 'btn-danger';
      case RaffleTicketStatus.SELECTED:
        return 'btn-primary';
      default:
        return 'Desconocido';
    }
  }

  onClickTicket(ticket: RaffleTicketDTO): void {
    if (ticket.status === RaffleTicketStatus.FREE) {
      this.reserveTicket(ticket.id);
    } else if (ticket.status === RaffleTicketStatus.SELECTED) {
      const user = this.authService.getUser();
      if (user.id === ticket.client?.id) {
        this.freeTicket(ticket.id);
      }
    }
  }

  reserveTicket(ticketId: number): void {
    this.loading = true;
    this.raffleTicketsService.reserveTicket(ticketId).subscribe({
      next: () => {
        this.loadTickets();
        this.loading = false;
        this.alertService.openInfoModal('Boleto reservado con éxito', 'Éxito');
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al reservar el boleto', 'Error');
        this.loading = false;
      }
    });
  }

  freeTicket(ticketId: number): void {
    this.loading = true;
    this.raffleTicketsService.freeTicket(ticketId).subscribe({
      next: () => {
        this.loadTickets();
        this.loading = false;
        this.alertService.openInfoModal('Boleto liberado con éxito', 'Éxito');
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al liberar el boleto', 'Error');
        this.loading = false;
      }
    });
  }

  buyMyTickets(): void {
    this.loading = true;
    this.selected.forEach((ticket, index) => {
      this.raffleTicketsService.buyTicket(ticket.id).subscribe({
        next: () => {
          //If is te las ticket, reload the page
          if (index === this.selected.length - 1) {
            this.loadTickets();
            this.loading = false;
            this.alertService.openInfoModal('Boletos comprados con éxito', 'Éxito');
          }
        },
        error: (error) => {
          this.alertService.openInfoModal('Error al comprar los boletos', 'Error');
          this.loading = false;
        }
      });
    });
  }
}
