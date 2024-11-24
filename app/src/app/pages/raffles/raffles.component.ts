import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '@components/footer/footer.component';
import { HeaderComponent } from '@components/header/header.component';
import { RafflesService } from './raffles.service';
import { RaffleDTO } from './raffles.types';
import { Router } from '@angular/router';
import { AlertService } from '@shared/services/alert.service';

@Component({
  selector: 'app-raffles',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './raffles.component.html',
  styleUrl: './raffles.component.scss',
  providers: [RafflesService]
})
export class RafflesComponent implements OnInit{

  raffles: RaffleDTO[] = [];

  constructor(
    private readonly rafflesService: RafflesService,
    private readonly router: Router,
    private readonly alertService: AlertService
  ){}

  ngOnInit(){
    this.loadRaffles();
  }

  loadRaffles(){
    this.rafflesService.findRaffles().subscribe({
      next: (raffles) => {
        this.raffles = raffles;
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al obtener los sorteos', 'Error');
      }
    });
  }

  goToRaffle(id: number){
    this.router.navigate([`/raffle/${id}/tickets`]);
  }
}
