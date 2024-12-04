import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '@components/footer/footer.component';
import { HeaderComponent } from '@components/header/header.component';
import { DebtorsService } from './debtors.service';
import { AlertService } from '@shared/services/alert.service';

@Component({
  selector: 'app-debtors',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './debtors.component.html',
  styleUrls: ['./debtors.component.scss'],
  providers: [DebtorsService],
})
export class DebtorsComponent implements OnInit {
  debtors: any[] = [];
  loading: boolean = false;
  constructor(private readonly debtorsService: DebtorsService,
    private readonly alert: AlertService
  ) {}

  ngOnInit() {
    this.findDebtors();
  }

  findDebtors() {
    this.loading = true;
    this.debtorsService.findDebtors().subscribe({
      next: (response) => {
        // Extraer todos los boletos y añadirlos a la lista de deudores
        const allTickets = response.flatMap((sorteo: any) => sorteo.boletos);
        this.debtors = allTickets.map((boleto: any) => ({
          numero: boleto.numeroBoleto,
          cliente: boleto.idCliente.nombre,
          correo: boleto.idCliente.correo,
          estado: boleto.estado,
          boletoId: boleto.id,
          fechaApartado: new Date(boleto.fechaLimApart).toLocaleDateString(),
          ultimoRecordatorio: null, // Aquí puedes definir cómo obtener esta información si aplica
        }));
        this.loading = false;
      },
      error: (error) => {
        this.alert.openInfoModal('Error al obtener los deudores', 'Error');
        this.loading = false;
      },
    });
  }

  sendReminder(debtor: any) {
    this.loading = true;
    if (debtor) {
      this.debtorsService.sendReminder(debtor).subscribe({
        next: () => {
          this.alert.openInfoModal('Recordatorio enviado correctamente', 'Éxito');
          this.loading = false;
        },
        error: (err) => {
          this.alert.openInfoModal('Error al enviar el recordatorio', 'Error');
          this.loading = false;
        },
      });
    }
  }
}
