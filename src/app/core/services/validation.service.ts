import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ValidationModel } from 'src/app/shared/models/validation.model';
import { PanDetailsModel } from 'src/app/shared/models/pan-details.model';
import { BankDetailsModel } from 'src/app/shared/models/bank-details.model';
import { SearchCriteriaModel } from 'src/app/shared/models/search-criteria.model';
import { SearchResultModel } from 'src/app/shared/models/search-result.model';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  constructor(private http: HttpClient) {}

  validateFlagsAndStatuses(applicationNo: string): Observable<any> {
    const url = `${environment.apiUrl}/validation/flags-statuses`;
    return this.http.post(url, { applicationNo });
  }

  setFieldVisibility(validationResults: any): void {
    // Logic to set field visibility based on validation results
  }

  validatePanDetails(panDetails: PanDetailsModel): boolean {
    // Logic to validate PAN details
    return true;
  }

  checkDocumentVerificationStatus(): Observable<boolean> {
    const url = `${environment.apiUrl}/validation/document-verification`;
    return this.http.get<boolean>(url);
  }

  validateIFSCCode(ifscCode: string): Observable<ValidationDTO> {
    const url = `${environment.apiUrl}/validation/ifsc-code`;
    return this.http.post<ValidationDTO>(url, { ifscCode });
  }

  validateDocuments(): Observable<boolean> {
    // Logic to validate documents
    return new Observable<boolean>((observer) => {
      observer.next(true);
      observer.complete();
    });
  }

  submitForm(): Observable<any> {
    const url = `${environment.apiUrl}/validation/submit-form`;
    return this.http.post<any>(url, {});
  }

  getBankDetails(ifscCode: string): Observable<BankDetailsModel> {
    const url = `${environment.apiUrl}/validation/bank-details?ifscCode=${ifscCode}`;
    return this.http.get<BankDetailsModel>(url);
  }

  validateAccountDetails(accountNumber: string, ifscCode: string): boolean {
    return accountNumber !== '' && ifscCode !== '';
  }

  validateDateDifference(fromDate: Date, toDate: Date): boolean {
    const diffInMonths = (toDate.getFullYear() - fromDate.getFullYear()) * 12 + (toDate.getMonth() - fromDate.getMonth());
    return toDate > fromDate && diffInMonths <= 6;
  }

  validateSelectedCase(selectedCase: any): Observable<boolean> {
    const url = `${environment.apiUrl}/validation/selected-case`;
    return this.http.post<boolean>(url, { selectedCase });
  }

  searchApplications(searchCriteria: SearchCriteriaModel): Observable<SearchResultModel[]> {
    const url = `${environment.apiUrl}/validation/search-applications`;
    return this.http.get<SearchResultModel[]>(url, { params: { ...searchCriteria } });
  }

  validatePartnerType(partnerType: string): boolean {
    return partnerType !== '';
  }

  validatePhNoPanLovChange(phNoPanCard: string, phNoPanLov: string): boolean {
    if (phNoPanCard === 'Y' && (phNoPanLov === '' || phNoPanLov === null)) {
      return false;
    }
    return true;
  }

  validatePanCard(panCardNumber: string): Observable<PanDetailsModel> {
    const url = `${environment.apiUrl}/validation/pan-card?panCardNumber=${panCardNumber}`;
    return this.http.get<PanDetailsModel>(url);
  }

  validatePanCardDetails(panDetails: PanDetailsModel): Observable<any> {
    if (!this.validatePanCardFormat(panDetails.panNumber)) {
      throw new Error('Invalid PAN card format');
    }
    return this.callExternalApi(panDetails);
  }

  validatePanCardFormat(panNumber: string): boolean {
    const panCardRegex = /^[A-Z]{3}[C,P,H,F,A,T,B,L,J,G][A-Z][0-9]{4}[A-Z]$/;
    return panCardRegex.test(panNumber.toUpperCase());
  }

  callExternalApi(panDetails: PanDetailsModel): Observable<any> {
    const url = `${environment.apiUrl}/validation/external-api`;
    return this.http.post<any>(url, panDetails);
  }
}
