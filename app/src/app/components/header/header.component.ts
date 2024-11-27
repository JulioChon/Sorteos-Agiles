import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavItem } from './header.types';
import { Router } from '@angular/router';
import { AuthService } from '@shared/services/auth/auth.service';
import { User } from '@shared/interfaces/user.interface';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

  items: NavItem[];
  isAdmin: boolean;
  adminItems: NavItem[];
  commonUserItems: NavItem[];
  user: User;

  constructor(
    private readonly router: Router,
    private readonly authService: AuthService
  ) {}

  ngOnInit() {
    this.initItems();
    this.user = this.isUserLoggedIn();
    if (this.user) {
      this.items.push({ title: 'Mis Boletos', route: '/my-tickets', icon: 'bi bi-ticket-fill' });
    }
    this.checkUserAdmin();
  }

  initItems() {
    this.items = [
      { title: 'Inicio', route: '/home', icon: 'bi bi-house-fill' },
      { title: 'Sorteos', route: '/raffles', icon: 'bi bi-gift-fill' },
      // { title: 'Sorteos', route: '/raffles', icon: 'bi bi-gift-fill' },
    ];
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }

  isUserLoggedIn(): User {
    return this.authService.getUser();
  }

  async checkUserAdmin(): Promise<void> {
    this.isAdmin = await this.isUserAnAdmin();
    if (this.isAdmin) {
      this.addAdminItems();
    }
  }

  isUserAnAdmin(): Promise<boolean> {
    const user = this.authService.getUser();
    return Promise.resolve(user?.admin);
  }

  addAdminItems() {
    this.adminItems = [
      { title: 'Crear Sorteo', route: '/admin/create-raffle', icon: 'bi bi-plus' },
      { title: 'Mis Sorteos' , route: '/admin/my-raffles', icon: 'bi bi-list' },
      { title: 'Configurar Recordatorio de Pago', route: '/admin/reminder-config', icon: 'bi bi-gear' }
    ];
  }

  logout() {
    this.authService.removeUser();
    this.router.navigate(['/sign-in']);
  }

  goToSignIn() {
    this.router.navigate(['/sign-in']);
  }

  goToSignUp() {
    this.router.navigate(['/sign-up']);
  }
}
