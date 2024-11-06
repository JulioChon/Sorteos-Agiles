import { Injectable } from '@angular/core';
import { Cliente } from '@shared/interfaces/cliente.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  saveUser(client: Cliente): void {
    localStorage.setItem('user', JSON.stringify(client));
  }

  getUser(): Cliente {
    return JSON.parse(localStorage.getItem('user'));
  }

  removeUser(): void {
    localStorage.removeItem('user');
  }
  
}
