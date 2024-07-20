export class ProposalForm {
  applicationNumber: string;
  documentViewerUrl: string;

  constructor(applicationNumber: string, documentViewerUrl: string) {
    this.applicationNumber = applicationNumber;
    this.documentViewerUrl = documentViewerUrl;
  }
}
