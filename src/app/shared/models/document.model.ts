export interface DocumentModel {
  documentName: string;
  documentType: string;
  amlDocumentType: string;
  proofType: string;
  requestCode: string;
  documentReceivedStatus: string;
}

export const documentReceivedStatusOptions: string[] = [
  'Received',
  'Pending',
  'Not Received'
];