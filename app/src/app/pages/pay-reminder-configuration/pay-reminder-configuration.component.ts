import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

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
  styleUrl: './pay-reminder-configuration.component.scss',
})
export class PayReminderConfigurationComponent implements OnInit {

  reminderForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {}

  initReminderForm(): void {
    this.reminderForm = this.formBuilder.group({
    });
  }
}
