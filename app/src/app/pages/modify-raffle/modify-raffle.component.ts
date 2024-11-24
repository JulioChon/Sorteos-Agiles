import { CommonModule, formatDate, Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Params, Raffle, Sorteo } from './modify-raffle.types';
import { ModifyRaffleService } from './modify-raffle.service';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { SelectImageComponent } from '../../components/select-image/select-image.component';
import { FirestorageService } from '../../shared/services/storage/firestorage.service';
import { AlertService } from '../../shared/services/alert.service';
import { RaffleStatus } from '@shared/types/raffle-status.enum';

@Component({
  selector: 'app-modify-raffle',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent,
    FooterComponent,
    SpinnerComponent,
    SelectImageComponent,
  ],
  templateUrl: './modify-raffle.component.html',
  styleUrls: ['./modify-raffle.component.scss'],
})
export class ModifyRaffleComponent implements OnInit {
  minTicketsRange = 1;
  private params: Params;
  raffle: Raffle;
  updateRaffleForm: FormGroup;
  updateImagePreview: string | ArrayBuffer | null = null;
  updateSelectedFile: File | null = null;
  showLoading: boolean = true;
  readonly defaultImage: string = 'https://placehold.co/300';
  raffleStatus = Object.values(RaffleStatus);

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly modifyRaffleService: ModifyRaffleService,
    private readonly formBuilder: FormBuilder,
    private readonly location: Location,
    private readonly fireStorage: FirestorageService,
    private readonly alertService: AlertService
  ) {}

  async ngOnInit(): Promise<void> {
    this.params = await this.parseUrlParams();
    this.findRaffleById(this.params.id);
  }

  parseUrlParams(): Promise<Params> {
    return new Promise((resolve) => {
      this.activatedRoute.params.subscribe((params) => {
        const mappedParams: Params = { id: params['id'] };
        resolve(mappedParams);
      });
    });
  }

  findRaffleById(id: string): void {
    this.showLoading = true;
    this.modifyRaffleService.findRaffleById(id).subscribe({
      next: (raffle: Raffle) => {
        this.raffle = raffle;
        this.createUpdateRaffleForm(raffle);
      },
      error: (error) => {},
      complete: () => {
        this.showLoading = false;
      },
    });
  }

  createUpdateRaffleForm(raffle: Raffle): void {
    this.updateRaffleForm = this.formBuilder.group({
      title: [raffle.title, [Validators.required, this.validateRaffleName()]],
      raffleImage: [raffle.raffleImage, [Validators.required]],
      ticketsMin: [
        raffle.minRange,
        [Validators.required, Validators.min(this.minTicketsRange)],
      ],
      ticketsMax: [
        raffle.maxRange,
        [Validators.required, this.validateTicketsMax()],
      ],
      startDate: [
        formatDate(raffle.startDate, 'yyyy-MM-dd', 'en'),
        [Validators.required, this.validateStartDate()],
      ],
      endDate: [
        formatDate(raffle.endDate, 'yyyy-MM-dd', 'en'),
        [Validators.required, this.validateEndDate()],
      ],
      raffleDate: [
        formatDate(raffle.raffleDate, 'yyyy-MM-dd', 'en'),
        [Validators.required, this.validateRaffleDate()],
      ],
      ticketPrice: [raffle.price, [Validators.required, Validators.min(1)]],
      status: [raffle.status, [Validators.required]],
    });

    // Revalidar `ticketsMax` si cambia `ticketsMin`
    this.updateRaffleForm.get('ticketsMin')?.valueChanges.subscribe(() => {
      this.updateRaffleForm.get('ticketsMax')?.updateValueAndValidity();
    });
  }


  validateDatesControllers(): void {
    this.startDate?.updateValueAndValidity();
    this.endDate?.updateValueAndValidity();
    this.raffleDate?.updateValueAndValidity();
  }

  validateTicketsControllers(): void {
    this.ticketsMax?.updateValueAndValidity();
    this.ticketsMin?.updateValueAndValidity();
  }

  get title() {
    return this.updateRaffleForm.get('title');
  }

  get raffleImage() {
    return this.updateRaffleForm.get('raffleImage');
  }

  get ticketsMax() {
    return this.updateRaffleForm.get('ticketsMax');
  }

  get ticketsMin() {
    return this.updateRaffleForm.get('ticketsMin');
  }

  get startDate() {
    return this.updateRaffleForm.get('startDate');
  }

  get endDate() {
    return this.updateRaffleForm.get('endDate');
  }

  get raffleDate() {
    return this.updateRaffleForm.get('raffleDate');
  }

  get ticketPrice() {
    return this.updateRaffleForm.get('ticketPrice');
  }

  get status() {
    return this.updateRaffleForm.get('status');
  }

  validateRaffleName(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value?.trim();

      if (!value || value.length <= 3) {
        return { invalidName: 'El nombre debe tener más de 3 caracteres' };
      }

      if (/^\s*$/.test(value)) {
        return { invalidName: 'El nombre no puede contener solo espacios en blanco' };
      }

      if (/\d/.test(value) && !/[a-zA-Z]/.test(value)) {
        return { invalidName: 'El nombre debe contener letras si incluye números' };
      }

      return null;
    };
  }

  validateStartDate(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (!control.value) {
        return { required: true };
      }
      const startDate = new Date(control.value + 'T00:00:00');
      const today = new Date();
      startDate.setHours(0, 0, 0, 0);
      today.setHours(0, 0, 0, 0);
      if (startDate.getTime() < today.getTime()) {
        return { invalidDate: 'La fecha no puede ser anterior a la de hoy' };
      }
      const year: string = startDate.getFullYear().toString();
      if (year.length > 4) {
        return { invalidDate: ' Año no válido' };
      }
      return null;
    };
  }

  validateEndDate(): ValidatorFn {
    return (control: AbstractControl) => {
      if (!control.value) {
        return { required: true };
      }
      const startDateValue = new Date(
        this.updateRaffleForm?.get('startDate')?.value
      );
      const endDate = new Date(control.value);
      startDateValue.setHours(0, 0, 0, 0);
      endDate.setHours(0, 0, 0, 0);
      if (endDate.getTime() < startDateValue.getTime()) {
        return { invalidDate: 'La fecha no puede ser anterior a la de inicio' };
      }
      if (endDate.getTime() === startDateValue.getTime()) {
        return { invalidDate: 'La fecha no puede ser igual a la de inicio' };
      }
      const year: string = endDate.getFullYear().toString();
      if (year.length > 4) {
        return { invalidDate: ' Año no válido' };
      }
      return null;
    };
  }

  validateRaffleDate(): ValidatorFn {
    return (control: AbstractControl) => {
      if (!control.value) {
        return { required: true };
      }
      const selectedDate = new Date(control.value);
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      if (selectedDate.getTime() < today.getTime()) {
        return { invalidDate: 'La fecha no puede ser anterior a la de hoy' };
      }
      const endDate = new Date(this.updateRaffleForm?.get('endDate')?.value);
      if (selectedDate.getTime() < endDate.getTime()) {
        return {
          invalidDate: 'La fecha no puede ser anterior a la de fin de venta',
        };
      }
      const startDate = new Date(this.updateRaffleForm?.get('startDate')?.value);
      if (selectedDate.getTime() < startDate.getTime()) {
        return {
          invalidDate: 'La fecha no puede ser anterior a la de inicio de venta',
        };
      }
      if (
        selectedDate.getTime() > startDate.getTime() &&
        selectedDate.getTime() < endDate.getTime()
      ) {
        return {
          invalidDate:
            'La fecha no puede estar en el periodo de venta del sorteo',
        };
      }
      if (selectedDate.getTime() === endDate.getTime()) {
        return {
          invalidDate: 'La fecha no puede ser igual a la de fin de venta',
        };
      }
      const year: string = selectedDate.getFullYear().toString();
      if (year.length > 4) {
        return { invalidDate: ' Año no válido' };
      }
      return null;
    };
  }

  onFileSelected(file: File): void {
    this.updateSelectedFile = file;
    const reader = new FileReader();
    reader.onload = () => {
      this.updateImagePreview = reader.result;
    };
    reader.readAsDataURL(file);
  }

  validateTicketsMax(): ValidatorFn {
    return (control: AbstractControl) => {
      if (!control.value) {
        return { required: true };
      }
      // Obtenemos el valor mínimo en cada cambio del campo
      const minTickets = this.updateRaffleForm?.get('ticketsMin')?.value;
      const maxTickets = control.value;

      // Validación: maxTickets no puede ser menor que minTickets
      if (maxTickets < minTickets) {
        return { invalidTickets: 'El valor no puede ser menor al mínimo' };
      }
      return null;
    };
  }

  onResetImage(): void {
    this.updateSelectedFile = null;
    this.updateImagePreview = null;
  }

  goBack(): void {
    this.location.back();
  }

  async onSubmit(): Promise<void> {
    this.validateDatesControllers();
    this.validateTicketsControllers();
    if (this.updateRaffleForm.invalid) {
      this.updateRaffleForm.markAllAsTouched();
      return;
    }

    let sorteoImg = '';
    if (this.updateSelectedFile) {
      sorteoImg = await this.fireStorage.updateImage(
        this.updateSelectedFile,
        this.raffle.raffleImage
      );
    } else {
      sorteoImg = this.raffle.raffleImage;
    }

    const toISOStringWithTimezone = (date: string): string => {
      //add 1 day to the date
      const newDate = new Date(date);
      newDate.setDate(newDate.getDate() + 1);
      return newDate.toISOString();
    };

    const sorteo: Sorteo = {
      id: this.raffle.id,
      nombre: this.title.value,
      imagenSorteo: sorteoImg,
      rangoMax: this.ticketsMax.value,
      rangoMin: this.ticketsMin.value,
      fechaInicioVenta: toISOStringWithTimezone(this.startDate.value),
      fechaFinVenta: toISOStringWithTimezone(this.endDate.value),
      fechaSorteo: toISOStringWithTimezone(this.raffleDate.value),
      precio: this.ticketPrice.value,
      estado: this.status.value,
    };

    this.modifyRaffleService.updateRaffle(sorteo).subscribe({
      next: (response) => {
        this.alertService.openInfoModal('Sorteo actualizado correctamente');
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al actualizar el sorteo');
      },
      complete: () => {
        this.goBack();
      },
    });
  }
}
