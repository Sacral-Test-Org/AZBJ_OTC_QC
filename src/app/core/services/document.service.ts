import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface Document {
  id: number;
  name: string;
  path: string;
}

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  private baseUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getDocuments(): Observable<Document[]> {
    const url = `${this.baseUrl}/documents`;
    return this.http.get<Document[]>(url);
  }

  generateDocumentUrl(applicationNumber: string): string {
    return `${this.baseUrl}/documents/${applicationNumber}`;
  }
}