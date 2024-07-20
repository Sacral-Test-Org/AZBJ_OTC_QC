export class BankDetailsModel {
  ifscCode: string;
  bankName: string;
  bankBranch: string;
  micrCode: string;

  constructor(ifscCode: string, bankName: string, bankBranch: string, micrCode: string) {
    this.ifscCode = ifscCode.toUpperCase(); // Ensure IFSC code is in uppercase
    this.bankName = bankName;
    this.bankBranch = bankBranch;
    this.micrCode = micrCode;
  }
}