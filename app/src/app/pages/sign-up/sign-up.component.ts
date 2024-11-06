import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
  ValidatorFn,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente } from './sign-up.types';
import { SignUpService } from './sign-up.service';
import { AlertService } from '@shared/services/alert.service';
import { AuthService } from '@shared/services/auth/auth.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
  providers: [SignUpService]
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly router: Router,
    private readonly signUpService: SignUpService,
    private readonly alertService: AlertService,
    private readonly authService: AuthService
  ) {}

  ngOnInit() {
    this.onCreateSignUpForm();
  }

  onCreateSignUpForm() {
    this.signUpForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      fullname: ['', [Validators.required]],
      cellphone: ['', [Validators.required, Validators.minLength(10)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          this.validateConfirmPassword(),
        ],
      ],
    });
  }

  validateConfirmPassword(): ValidatorFn {
    return (control: FormControl) => {
      const password = this.password?.value;
      const confirmPassword = control.value;
      return password === confirmPassword ? null : { passwordNotMatch: true };
    };
  }

  goToSignIn() {
    this.router.navigate(['/sign-in']);
  }

  onSubmit() {
    if (this.signUpForm.invalid) {
      this.signUpForm.markAllAsTouched();
      return;
    }
    const client: Cliente = {
      nombre: this.fullname?.value,
      correo: this.email?.value,
      telefono: this.cellphone?.value,
      contrasenia: this.password?.value,
    }
    this.signUpService.signUp(client).subscribe({
      next: (client) => {
        this.authService.saveUser(client);
        this.alertService.openInfoModal('Usuario registrado correctamente');
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error(error);
      },
    });
    // console.log(this.signUpForm.value);
  }

  get email() {
    return this.signUpForm?.get('email');
  }

  get fullname() {
    return this.signUpForm?.get('fullname');
  }

  get cellphone() {
    return this.signUpForm?.get('cellphone');
  }

  get password() {
    return this.signUpForm?.get('password');
  }

  get confirmPassword() {
    return this.signUpForm?.get('confirmPassword');
  }
}
