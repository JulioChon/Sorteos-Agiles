import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { RaffleGalleryComponent } from '../../components/raffle-gallery/raffle-gallery.component';
import { RaffleStatus } from '../../shared/types/raffle-status.enum';

@Component({
  selector: 'app-my-raffles',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent, SpinnerComponent, RaffleGalleryComponent],
  templateUrl: './my-raffles.component.html',
  styleUrl: './my-raffles.component.scss'
})
export class MyRafflesComponent implements OnInit {

  showLoading: boolean = true;
  raffles: any[] = [];

  constructor() { }

  ngOnInit() {
    setTimeout(() => {
      this.raffles = this.findRafflesByUserId();
      this.showLoading = false;
  }, 3000);
  }
  

  findRafflesByUserId() {
    //TODO: Implement service call
    return [
      {
        id: 1,
        nombre: 'Sorteo de prueba',
        imagenSorteo: 'https://placehold.co/200',
        estado: RaffleStatus.ACTIVO
      },
      {
        id: 1,
        nombre: 'Sorteo de prueba',
        imagenSorteo: 'https://placehold.co/200',
        estado: RaffleStatus.INACTIVO
      },
      {
        id: 1,
        nombre: 'Sorteo de prueba',
        imagenSorteo: 'https://placehold.co/200',
        estado: RaffleStatus.CANCELADO
      },
      {
        id: 1,
        nombre: 'Sorteo de prueba',
        imagenSorteo: 'https://placehold.co/200',
        estado: RaffleStatus.INACTIVO
      },
    ];
  }

}
