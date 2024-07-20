export class DocumentUploadModel {
  documentName: string;
  documentType: string;
  documentContent: Blob;

  constructor(documentName: string, documentType: string, documentContent: Blob) {
    this.documentName = documentName;
    this.documentType = documentType;
    this.documentContent = documentContent;
  }
}
