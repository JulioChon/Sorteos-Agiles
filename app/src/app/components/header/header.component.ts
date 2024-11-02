import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavItem } from './header.types';
import { Router } from '@angular/router';

@Component({
  selector: 'header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

  items: NavItem[];
  isAdmin: boolean;
  adminItems: NavItem[];

  constructor(
    private readonly router: Router
  ) {}

  ngOnInit() {
    this.initItems();
    this.checkUserAdmin();
  }

  initItems() {
    this.items = [
      { title: 'Inicio', route: '/home', icon: 'bi bi-house-fill' },
    ];
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }

  async checkUserAdmin(): Promise<void> {
    this.isAdmin = await this.isUserAnAdmin();
    if (this.isAdmin) {
      this.addAdminItems();
    }
  }

  isUserAnAdmin(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      resolve(true);//TODO: Implement user check
    });
  }

  addAdminItems() {
    this.adminItems = [
      { title: 'Crear Sorteo', route: '/admin/create-raffle', icon: 'bi bi-plus' },
      { title: 'Mis Sorteos' , route: '/admin/my-raffles', icon: 'bi bi-list' }
    ];
  }
}
