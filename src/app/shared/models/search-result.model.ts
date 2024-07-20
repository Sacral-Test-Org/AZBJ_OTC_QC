export class SearchResultModel {
  applicationNo: string;
  proposalStatus: string;
  receiptNo: string;
  receiptDate: Date;
  applicantName: string;
  policyholderName: string;
  partnerName: string;
  documentStatus: string;

  constructor(
    applicationNo: string,
    proposalStatus: string,
    receiptNo: string,
    receiptDate: Date,
    applicantName: string,
    policyholderName: string,
    partnerName: string,
    documentStatus: string
  ) {
    this.applicationNo = applicationNo;
    this.proposalStatus = proposalStatus;
    this.receiptNo = receiptNo;
    this.receiptDate = receiptDate;
    this.applicantName = applicantName;
    this.policyholderName = policyholderName;
    this.partnerName = partnerName;
    this.documentStatus = documentStatus;
  }
}