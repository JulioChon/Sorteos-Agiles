import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '@components/footer/footer.component';
import { HeaderComponent } from '@components/header/header.component';
import { MyTicketsService } from './my-tickets.service';
import { AuthService } from '@shared/services/auth/auth.service';
import { BoletoDTO } from './my-tickets.types';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-my-tickets',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './my-tickets.component.html',
  styleUrl: './my-tickets.component.scss'
})
export class MyTicketsComponent implements OnInit {

  myTickets: BoletoDTO[] = [];

  constructor(private readonly myTicketsService: MyTicketsService,
    private readonly storage: AuthService
  ) { }

  ngOnInit(): void {
    this.findMyTickets();
  }

  findMyTickets() {
    const user = this.storage.getUser();
    this.myTicketsService.findMyTickets(user.id).subscribe({
      next: (res) => {
        Array.prototype.push.apply(this.myTickets, res);
      },
    });
  }

}
