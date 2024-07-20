import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UnderwriterComment } from 'src/app/shared/models/underwriter-comment.model';

@Injectable({
  providedIn: 'root'
})
export class UnderwriterCommentService {
  private contractId: string;
  private userId: string;

  constructor(private http: HttpClient) {}

  getComments(userId: string, controlProfile: string, contractId: string): Observable<UnderwriterComment[]> {
    let url = `${environment.apiUrl}/comments?contractId=${contractId}`;
    if (userId.startsWith('P00%') || controlProfile !== '1') {
      url += '&all=true';
    } else if (controlProfile === '1') {
      url += '&flag=N';
    }
    return this.http.get<UnderwriterComment[]>(url);
  }

  captureContractAndUserId(contractId: string, userId: string): void {
    this.contractId = contractId;
    this.userId = userId;
  }

  submitComment(comment: string): Observable<any> {
    const url = `${environment.apiUrl}/comments`;
    const body = {
      contractId: this.contractId,
      userId: this.userId,
      comment: comment
    };
    return this.http.post(url, body);
  }
}
