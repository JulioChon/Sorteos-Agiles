import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from './sign-up.types';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(
    private readonly http: HttpClient
  ) { }

  signUp(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${environment.api}/cliente`, cliente);
  }
}
