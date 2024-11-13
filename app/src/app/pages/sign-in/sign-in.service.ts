import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './sign-in.types';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  constructor(
    private readonly http: HttpClient
  ) { }

  signIn(email: string, contrasenia: string): Observable<User> {
    return this.http.get<User>(`${environment.api}/cliente/login`, { params: { email, contrasenia } });
  }
}
