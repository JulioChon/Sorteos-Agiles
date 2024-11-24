import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { FirestorageService } from '../../shared/services/storage/firestorage.service';
import { FooterComponent } from '../../components/footer/footer.component';
import { Raffle } from './create-raffle.types';
import { CreateRaffleService } from './create-raffle.service';
import { RaffleStatus } from '../../shared/types/raffle-status.enum';
import { Router } from '@angular/router';
import { AlertService } from '@shared/services/alert.service';

@Component({
  selector: 'app-create-raffle',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './create-raffle.component.html',
  styleUrls: ['./create-raffle.component.scss'],
  providers: [FirestorageService, CreateRaffleService],
})
export class CreateRaffleComponent {
  minTicketsRange = 1;
  createRaffleForm: FormGroup;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly fireStorage: FirestorageService,
    private readonly createRaffleService: CreateRaffleService,
    private readonly router: Router,
    private readonly alert: AlertService
  ) {}

  ngOnInit(): void {
    this.initFormCreateRaffle();
  }

  initFormCreateRaffle() {
    this.createRaffleForm = this.formBuilder.group({
      title: [
        '',
        [
          Validators.required,
          this.validateRaffleName(),
          Validators.maxLength(50),
        ],
      ],
      startDate: ['', [Validators.required, this.validateStartDate()]],
      endDate: ['', [Validators.required, this.validateEndDate()]],
      raffleDate: ['', [Validators.required, this.validateRaffleDate()]],
      ticketsMin: [
        this.minTicketsRange,
        [Validators.required, Validators.min(this.minTicketsRange)],
      ],
      ticketsMax: ['', [Validators.required, this.validateTicketsMax()]],
      ticketPrice: [null, [Validators.required, Validators.min(1)]],
      image: [null],
    });
  }

  get title() {
    return this.createRaffleForm.get('title');
  }

  get startDate() {
    return this.createRaffleForm.get('startDate');
  }

  get endDate() {
    return this.createRaffleForm.get('endDate');
  }

  get raffleDate() {
    return this.createRaffleForm.get('raffleDate');
  }

  get ticketsMin() {
    return this.createRaffleForm.get('ticketsMin');
  }

  get ticketsMax() {
    return this.createRaffleForm.get('ticketsMax');
  }

  get image() {
    return this.createRaffleForm.get('image');
  }

  get ticketPrice() {
    return this.createRaffleForm.get('ticketPrice');
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

  validateRaffleName(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value = control.value?.trim();

      if (!value || value.length <= 3) {
        return { invalidName: 'El nombre debe tener más de 3 caracteres' };
      }

      if (/^\s*$/.test(value)) {
        return {
          invalidName: 'El nombre no puede contener solo espacios en blanco',
        };
      }

      if (/\d/.test(value) && !/[a-zA-Z]/.test(value)) {
        return {
          invalidName: 'El nombre debe contener letras si incluye números',
        };
      }

      return null;
    };
  }

  validateStartDate(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (!control.value) {
        return { required: true };
      }
      const selectedDate = new Date(control.value + 'T00:00:00');
      const today = new Date();
      selectedDate.setHours(0, 0, 0, 0);
      today.setHours(0, 0, 0, 0);
      if (selectedDate.getTime() < today.getTime()) {
        return { invalidDate: 'La fecha no puede ser anterior a la de hoy' };
      }
      const year: string = selectedDate.getFullYear().toString();
      if (year.length > 4) {
        return { invalidDate: 'Año no válido' };
      }
      return null;
    };
  }

  validateEndDate(): ValidatorFn {
    return (control: AbstractControl) => {
      if (!control.value) {
        return { required: true };
      }
      const startDate = new Date(this.startDate?.value + 'T00:00:00');
      const endDate = new Date(control.value + 'T00:00:00');

      startDate.setHours(0, 0, 0, 0);
      endDate.setHours(0, 0, 0, 0);

      if (endDate.getTime() < startDate.getTime()) {
        return { invalidDate: 'La fecha no puede ser anterior a la de inicio' };
      }

      if (endDate.getTime() === startDate.getTime()) {
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
      const endDate = new Date(this.endDate?.value);
      if (selectedDate.getTime() < endDate.getTime()) {
        return {
          invalidDate: 'La fecha no puede ser anterior a la de fin de venta',
        };
      }
      const startDate = new Date(this.startDate?.value);
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

  validateTicketsMax(): ValidatorFn {
    return (control: AbstractControl) => {
      if (!control.value) {
        return { required: true };
      }
      // Obtenemos el valor mínimo en cada cambio del campo
      const minTickets = this.createRaffleForm?.get('ticketsMin')?.value;
      const maxTickets = control.value;

      // Validación: maxTickets no puede ser menor que minTickets
      if (maxTickets < minTickets) {
        return { invalidTickets: 'El valor no puede ser menor al mínimo' };
      }
      return null;
    };
  }

  onImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result; // Almacenar la URL de la imagen
      };
      reader.readAsDataURL(file);
    }
  }



  async onSubmit() {
    this.validateDatesControllers();
    this.validateTicketsControllers();
    if (this.createRaffleForm.invalid) {
      this.createRaffleForm.markAllAsTouched();
      return;
    }
    const photoUrl = await this.fireStorage.uploadImage(this.selectedFile);
    const toISOStringWithTimezone = (date: string): string => {
      //add 1 day to the date
      const newDate = new Date(date);
      newDate.setDate(newDate.getDate() + 1);
      return newDate.toISOString();
    };
    const raffle: Raffle = {
      nombre: this.title?.value,
      imagenSorteo: photoUrl,
      rangoMax: this.ticketsMax?.value,
      rangoMin: this.ticketsMin?.value,
      fechaInicioVenta: toISOStringWithTimezone(this.startDate.value),
      fechaFinVenta: toISOStringWithTimezone(this.endDate.value),
      fechaSorteo: toISOStringWithTimezone(this.raffleDate.value),
      estado: RaffleStatus.ACTIVO,
      precio: this.ticketPrice?.value,
    };
    this.createRaffleService.createRaffle(raffle).subscribe({
      next: (raffle) => {
        this.router.navigate(['/admin/my-raffles']);
      },
      error: (error) => {
        this.fireStorage.removeImage(photoUrl);
        this.alert.openInfoModal(
          'Error al crear el sorteo, revisa los detalles',
          'Por favor, intenta de nuevo'
        );
      },
    });
  }
}
