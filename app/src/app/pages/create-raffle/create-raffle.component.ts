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
import { File } from 'buffer';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
  selector: 'app-create-raffle',
  standalone: true,
  imports: [CommonModule, HeaderComponent ,FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './create-raffle.component.html',
  styleUrls: ['./create-raffle.component.scss'],
  providers: [FirestorageService],
})
export class CreateRaffleComponent {
  minTicketsRange = 1;
  createRaffleForm: FormGroup;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly fireStorage: FirestorageService
  ) {}

  ngOnInit(): void {
    this.initFormCreateRaffle();
  }

  initFormCreateRaffle() {
    this.createRaffleForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      startDate: ['', [Validators.required, this.validateStartDate()]],
      endDate: ['', [Validators.required, this.validateEndDate()]],
      ticketsMin: [
        this.minTicketsRange,
        [Validators.required, Validators.min(this.minTicketsRange)],
      ],
      ticketsMax: ['', [Validators.required, this.validateTicketsMax()]],
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

  get ticketsMin() {
    return this.createRaffleForm.get('ticketsMin');
  }

  get ticketsMax() {
    return this.createRaffleForm.get('ticketsMax');
  }

  get image() {
    return this.createRaffleForm.get('image');
  }

  validateStartDate(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (!control.value) {
        return { required: true };
      }
      const selectedDate = new Date(control.value);
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      if (selectedDate < today) {
        return { invalidDate: 'La fecha no puede ser anterior a la de hoy' };
      }
      const year: string = selectedDate.getFullYear().toString();
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
      const startDate = new Date(this.createRaffleForm.get('startDate').value);
      const endDate = new Date(control.value);
      if (endDate < startDate) {
        return { invalidDate: 'La fecha no puede ser anterior a la de inicio' };
      }
      const year: string = endDate.getFullYear().toString();
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
    if (this.createRaffleForm.invalid) {
      this.createRaffleForm.markAllAsTouched();
      return;
    }
    const photoUrl = await this.fireStorage.uploadImage(this.selectedFile);
  }
}
