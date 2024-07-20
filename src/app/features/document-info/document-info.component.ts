import { Component, OnInit } from '@angular/core';
import { ValidationService } from 'src/app/core/services/validation.service';
import { DocumentService } from 'src/app/core/services/document.service';
import { DocumentModel } from 'src/app/shared/models/document.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-document-info',
  templateUrl: './document-info.component.html',
  styleUrls: ['./document-info.component.css']
})
export class DocumentInfoComponent implements OnInit {
  documents: DocumentModel[] = [];
  documentReceivedStatusOptions: string[] = ['Received', 'Not Received', 'Pending'];

  constructor(private validationService: ValidationService, private documentService: DocumentService) {}

  ngOnInit(): void {
    this.validationService.getDocumentData().subscribe((data: DocumentModel[]) => {
      this.documents = data;
    });
  }

  updateDocumentReceivedStatus(documentId: string, status: string): void {
    this.validationService.updateDocumentStatus(documentId, status).subscribe(() => {
      const document = this.documents.find(doc => doc.id === documentId);
      if (document) {
        document.receivedStatus = status;
      }
    });
  }

  onViewAllDocumentsClick(): void {
    this.documentService.getDocuments().subscribe((documents: DocumentModel[]) => {
      documents.forEach(document => {
        if (document.path) {
          this.documentService.downloadDocument(document.path).subscribe(file => {
            const blob = new Blob([file], { type: document.type });
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = document.name;
            a.click();
            window.URL.revokeObjectURL(url);
          });
        }
      });
    });
  }
}
