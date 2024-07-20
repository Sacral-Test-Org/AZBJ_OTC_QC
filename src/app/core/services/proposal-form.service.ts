import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProposalFormService {
  private apiUrl = `${environment.apiUrl}/generateDocumentViewerUrl`;

  constructor(private http: HttpClient) {}

  generateDocumentViewerUrl(applicationNumber: string): Observable<string> {
    const url = `${this.apiUrl}?applicationNumber=${applicationNumber}`;
    return this.http.get<string>(url);
  }
}
