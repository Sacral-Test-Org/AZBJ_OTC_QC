import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DocumentUploadModel } from 'src/app/shared/models/document-upload.model';

@Injectable({
  providedIn: 'root'
})
export class DocumentUploadService {
  private apiUrl = 'http://localhost:8080/api/documents/upload';

  constructor(private http: HttpClient) { }

  uploadDocument(document: DocumentUploadModel): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', document.file);
    formData.append('description', document.description);

    return this.http.post<any>(this.apiUrl, formData);
  }
}