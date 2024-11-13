import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfiguracionRecordatorioPago } from './pay-reminder-configuration.types';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class PayReminderConfigurationService {

  constructor(
    private readonly http: HttpClient
  ) { }

  //TODO: Encontrar si hay un endpoint para obtener la configuración de recordatorio de pago

  createConfiguration(reminderConfig: ConfiguracionRecordatorioPago): Observable<any> {
    return this.http.post<any>(`${environment.api}/boletos/config`, reminderConfig, { responseType: 'text' as 'json' });
  }
}
