import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { RaffleGalleryComponent } from '../../components/raffle-gallery/raffle-gallery.component';
import { RaffleStatus } from '../../shared/types/raffle-status.enum';
import { RaffleDTO } from './my-raffles.types';
import { MyRafflesService } from './my-raffles.service';
import { Router } from '@angular/router';
import { AlertService } from '../../shared/services/alert.service';

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
  galleryButtonActions: any[] = [];
  showDeleteModal: boolean = false;

  constructor(
    private readonly myRafflesService: MyRafflesService,
    private readonly router: Router,
    private readonly alertService: AlertService
  ) {}

  ngOnInit() {
    this.findRafflesByUserId();
    this.initButtonActions();
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

  openModifyPage(raffleId: string) {
    this.router.navigate(['admin/modify-raffle', raffleId]);
  }

  initButtonActions() {
    this.galleryButtonActions = [
      {
        label: 'Editar',
        cssClass: 'btn btn-primary',
        action: (raffleId: string) => this.openModifyRaffle(raffleId),
      },
      {
        label: 'Eliminar',
        cssClass: 'btn btn-danger',
        action: (raffleId: string) => this.deleteRaffle(raffleId),
      }
    ]
  }

  private openModifyRaffle(raffleId: string) {
    this.router.navigate(['admin/modify-raffle', raffleId]);
  }

  private async deleteRaffle(raffleId: string) {
    const message = await this.alertService.openConfirmModal('¿Estás seguro de que deseas eliminar este sorteo?', 'Eliminar sorteo');
  }
}
