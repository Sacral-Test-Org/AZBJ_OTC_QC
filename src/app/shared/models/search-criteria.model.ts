export class SearchCriteriaModel {
  applicationNo: string;
  partnerType: string;
  fromDate: Date;
  toDate: Date;

  constructor(applicationNo: string, partnerType: string, fromDate: Date, toDate: Date) {
    this.applicationNo = applicationNo;
    this.partnerType = partnerType;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }
}