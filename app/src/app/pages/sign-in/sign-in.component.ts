import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { SignInService } from './sign-in.service';
import { AuthService } from '@shared/services/auth/auth.service';
import { Router } from '@angular/router';
import { HeaderComponent } from "../../components/header/header.component";
import { FooterComponent } from "../../components/footer/footer.component";

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, HeaderComponent, FooterComponent],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss',
  providers: [SignInService]
})
export class SignInComponent implements OnInit {

  signInForm: FormGroup;
  showMessageError: boolean;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly signInService: SignInService,
    private readonly authService: AuthService,
    private readonly router: Router
  ) { }

  ngOnInit() {
    this.initSignInForm();
  }

  initSignInForm() {
    this.signInForm = this.formBuilder.group({
      email: ['',[Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.minLength(8)]]
    });
  }

  get email() {
    return this.signInForm.get('email');
  }

  get password() {
    return this.signInForm.get('password');
  }

  signIn() {
    if (this.signInForm.invalid) {
      this.signInForm.markAllAsTouched();
      this.showMessageError = true;
    }
    this.signInService.signIn(this.signInForm.value.email, this.signInForm.value.password).subscribe({
      next: (user) => {
        this.authService.saveUser(user);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.showMessageError = true;
      }
    });
  }

  goToSignUp() {
    this.router.navigate(['/sign-up']);
  }
}
