import { DocumentModel } from './document.model';

export class ValidationModel {
  applicationNo: string;
  otcEligible: boolean;
  spgCase: boolean;
  kycFlag: boolean;
  message: string;
  documentData: DocumentModel[];

  constructor() {
    this.applicationNo = '';
    this.otcEligible = false;
    this.spgCase = false;
    this.kycFlag = false;
    this.message = '';
    this.documentData = [];
  }
}