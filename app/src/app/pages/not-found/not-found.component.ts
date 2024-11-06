import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { set } from '@angular/fire/database';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.scss',
})
export class NotFoundComponent implements OnInit {
  
  constructor(private readonly router: Router) {}

  ngOnInit() {
    this.onTimeout(5000);
  }

  onTimeout(time: number) {
    setTimeout(() => {
      this.redirectToHome();
    }, time);
  }

  redirectToHome() {
    this.router.navigate(['home']);
  }
}
