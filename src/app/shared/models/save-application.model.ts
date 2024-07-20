export class SaveApplicationModel {
  applicationNumber: string;
  contractId: string;
  status: string;
  comments: string;

  constructor(applicationNumber: string, contractId: string, status: string, comments: string) {
    this.applicationNumber = applicationNumber;
    this.contractId = contractId;
    this.status = status;
    this.comments = comments;
  }
}