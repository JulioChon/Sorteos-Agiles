import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '@shared/services/auth/auth.service';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class DebtorsService {

  constructor(
    private readonly http: HttpClient,
    private readonly auth: AuthService
  ) { }


  findDebtors(): Observable<any> {
    const user = this.auth.getUser();
    return this.http.get<any>(`${environment.api}/sorteos/deudores/${user.id}`);
  }

  sendReminder(debtor: any): Observable<any> {
    return this.http.post<any>(`${environment.api}/config/correo/enviar`, { idBoleto: debtor.boletoId, correo: debtor.correo }, { responseType: 'text' as 'json' });
  }
}
