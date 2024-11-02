import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent implements OnInit {

  signInForm: FormGroup;
  showMessageError: boolean;

  constructor(
    private readonly formBuilder: FormBuilder
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

  signIn() {
    if (this.signInForm.invalid) {
      this.showMessageError = true;
    }
  }  
}
