import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { RaffleGalleryComponent } from '../../components/raffle-gallery/raffle-gallery.component';
import { RaffleStatus } from '../../shared/types/raffle-status.enum';
import { RaffleDTO } from './my-raffles.types';
import { MyRafflesService } from './my-raffles.service';

@Component({
  selector: 'app-my-raffles',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    SpinnerComponent,
    RaffleGalleryComponent,
  ],
  templateUrl: './my-raffles.component.html',
  styleUrl: './my-raffles.component.scss',
  providers: [MyRafflesService],
})
export class MyRafflesComponent implements OnInit {
  showLoading: boolean = true;
  raffles: RaffleDTO[] = [];

  constructor(private readonly myRafflesService: MyRafflesService) {}

  ngOnInit() {
    this.findRafflesByUserId();
  }

  findRafflesByUserId() {
    this.myRafflesService.getMyRaffles().subscribe({
      next: (raffles) => {
        Array.prototype.push.apply(this.raffles, raffles);
      },
      error: (error) => {
        console.error(error);
      },
      complete: () => {
        this.showLoading = false;
      },
    });
  }
}
