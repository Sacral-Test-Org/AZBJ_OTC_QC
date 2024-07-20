import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RelationshipType } from '../models/relationship-type.model';

@Injectable({
  providedIn: 'root'
})
export class RelationshipTypeService {
  private apiUrl = `${environment.apiUrl}/relationship-types`;

  constructor(private http: HttpClient) { }

  getRelationshipTypes(): Observable<RelationshipType[]> {
    return this.http.get<RelationshipType[]>(this.apiUrl);
  }
}
