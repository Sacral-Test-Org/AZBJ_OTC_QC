import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RejectDTO } from '../models/reject.dto';

@Injectable({
  providedIn: 'root'
})
export class RejectService {
  private apiUrl = `${environment.apiUrl}/reject`;

  constructor(private http: HttpClient) {}

  rejectApplication(rejectData: RejectDTO): Observable<any> {
    return this.http.post<any>(this.apiUrl, rejectData);
  }
}
