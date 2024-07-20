import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SaveApplicationModel } from '../models/save-application.model';

@Injectable({
  providedIn: 'root'
})
export class SaveApplicationService {
  private apiUrl = `${environment.apiUrl}/save-application-details`;

  constructor(private http: HttpClient) {}

  saveApplicationDetails(saveApplicationModel: SaveApplicationModel): Observable<any> {
    return this.http.post<any>(this.apiUrl, saveApplicationModel);
  }
}