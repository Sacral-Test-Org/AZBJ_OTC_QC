export class ApplicationDetailsModel {
  applicationNumber: string;
  applicationStatus: string;
  receiptNumber: string;
  receiptDate: Date;
  laName: string;
  phName: string;
  changeDescription: string;
  proposalNumber: string;
  partnerName: string;
  documentStatus: string;
  pendingDocuments: string;
  isChecked: boolean;

  constructor(
    applicationNumber: string,
    applicationStatus: string,
    receiptNumber: string,
    receiptDate: Date,
    laName: string,
    phName: string,
    changeDescription: string,
    proposalNumber: string,
    partnerName: string,
    documentStatus: string,
    pendingDocuments: string,
    isChecked: boolean
  ) {
    this.applicationNumber = applicationNumber;
    this.applicationStatus = applicationStatus;
    this.receiptNumber = receiptNumber;
    this.receiptDate = receiptDate;
    this.laName = laName;
    this.phName = phName;
    this.changeDescription = changeDescription;
    this.proposalNumber = proposalNumber;
    this.partnerName = partnerName;
    this.documentStatus = documentStatus;
    this.pendingDocuments = pendingDocuments;
    this.isChecked = isChecked;
  }
}