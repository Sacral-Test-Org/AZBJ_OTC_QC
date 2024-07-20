import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PartnerType } from '../models/partner-type.model';

@Injectable({
  providedIn: 'root'
})
export class PartnerTypeService {
  private apiUrl = `${environment.apiUrl}/partner-types`;

  constructor(private http: HttpClient) { }

  getPartnerTypes(): Observable<PartnerType[]> {
    return this.http.get<PartnerType[]>(this.apiUrl);
  }
}
