import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
  AbstractControl,
} from '@angular/forms';
import { PayReminderConfigurationService } from './pay-reminder-configuration.service';
import { ConfiguracionRecordatorioPago } from './pay-reminder-configuration.types';
import { AlertService } from '@shared/services/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pay-reminder-configuration',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './pay-reminder-configuration.component.html',
  styleUrls: ['./pay-reminder-configuration.component.scss'],
  providers: [PayReminderConfigurationService],
})
export class PayReminderConfigurationComponent implements OnInit {
  reminderForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly payReminderConfigurationService: PayReminderConfigurationService,
    private readonly alertService: AlertService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.initReminderForm();
  }

  initReminderForm(): void {
    this.reminderForm = this.formBuilder.group(
      {
        days: [0, [Validators.min(0)]],
        hours: [0, [Validators.min(0), Validators.max(23)]],
        minutes: [0, [Validators.min(0), Validators.max(59)]],
        seconds: [0, [Validators.min(0), Validators.max(59)]],
      },
      { validators: [this.atLeastOneTimeFieldValidator] } // Validator a nivel de formulario
    );
  }

  atLeastOneTimeFieldValidator(form: AbstractControl) {
    const { days, hours, minutes, seconds } = form.value;
    if (days > 0 || hours > 0 || minutes > 0 || seconds > 0) {
      return null;
    }
    return { atLeastOne: true };
  }

  onSubmit(): void {
    if (this.reminderForm.invalid) {
      this.reminderForm.markAllAsTouched();
      return;
    }
    const configuracion : ConfiguracionRecordatorioPago = {
      dias: this.days.value,
      horas: this.hours.value,
      minutos: this.minutes.value,
      segundos: this.seconds.value
    }
    this.payReminderConfigurationService.createConfiguration(configuracion).subscribe({
      next: () => {
        this.alertService.openInfoModal('Configuración guardada correctamente');
        this.router.navigate(['/admin']);
      },
      error: (error) => {
        this.alertService.openInfoModal('Error al guardar la configuración');
      }
  });
  }

  // Getters para los campos del formulario
  get days() {
    return this.reminderForm.get('days');
  }

  get hours() {
    return this.reminderForm.get('hours');
  }

  get minutes() {
    return this.reminderForm.get('minutes');
  }

  get seconds() {
    return this.reminderForm.get('seconds');
  }
}
