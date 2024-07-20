import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class KycValidationService {
  private kycValidationUrl = `${environment.apiUrl}/kyc/validate`;

  constructor(private http: HttpClient) {}

  downloadKycDoc(jsonString: string): Observable<any> {
    return this.http.post<any>(this.kycValidationUrl, jsonString);
  }
}
