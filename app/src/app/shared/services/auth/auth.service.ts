import { Injectable } from '@angular/core';
import { User } from '@shared/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  saveUser(user: User): void {
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUser(): User {
    return JSON.parse(localStorage.getItem('user'));
  }

  removeUser(): void {
    localStorage.removeItem('user');
  }
  
}
