import { Component, OnInit } from '@angular/core';
import { ValidationService } from 'src/app/core/services/validation.service';
import { ImageDetailsService } from 'src/app/features/image-details/image-details.service';
import { ProposalDetailsService } from 'src/app/features/proposal-details/proposal-details.service';
import { KycValidationService } from 'src/app/core/services/kyc-validation.service';
import { UnderwriterCommentService } from 'src/app/core/services/underwriter-comment.service';
import { RejectService } from 'src/app/core/services/reject.service';
import { DocumentService } from 'src/app/core/services/document.service';
import { PartnerTypeService } from 'src/app/core/services/partner-type.service';
import { RelationshipTypeService } from 'src/app/core/services/relationship-type.service';
import { BankDetailsService } from 'src/app/core/services/bank-details.service';
import { AccountDetailsService } from 'src/app/core/services/account-details.service';
import { Observable } from 'rxjs';
import { ImageDetailsModel } from 'src/app/shared/models/image-details.model';
import { ProposalDetailsModel } from 'src/app/shared/models/proposal-details.model';
import { UnderwriterComment } from 'src/app/shared/models/underwriter-comment.model';
import { SearchCriteriaModel, SearchResultModel } from 'src/app/shared/models/validation.model';
import { BankDetailsModel } from 'src/app/shared/models/bank-details.model';
import { AccountDetailsModel } from 'src/app/shared/models/account-details.model';
import { ValidationDTO } from 'src/app/shared/models/validation.model';
import { DeqcSearchModel } from 'src/app/shared/models/deqc-search.model';
import { DocumentModel } from 'src/app/shared/models/document.model';
import { PartnerType } from 'src/app/shared/models/partner-type.model';
import { RelationshipType } from 'src/app/shared/models/relationship-type.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-otc-qc',
  templateUrl: './otc-qc.component.html',
  styleUrls: ['./otc-qc.component.css']
})
export class OtcQcComponent implements OnInit {
  testNumber: string;
  documentReceivedStatus: string;
  imageDetails: ImageDetailsModel;
  proposalDetails: ProposalDetailsModel;
  comments: UnderwriterComment[] = [];
  searchResults: SearchResultModel[] = [];
  partnerTypes: PartnerType[] = [];
  relationshipTypes: RelationshipType[] = [];
  bankDetails: BankDetailsModel;
  accountDetails: AccountDetailsModel;
  isReasonLinkVisible: boolean = false;

  constructor(
    private validationService: ValidationService,
    private imageDetailsService: ImageDetailsService,
    private proposalDetailsService: ProposalDetailsService,
    private kycValidationService: KycValidationService,
    private underwriterCommentService: UnderwriterCommentService,
    private rejectService: RejectService,
    private documentService: DocumentService,
    private partnerTypeService: PartnerTypeService,
    private relationshipTypeService: RelationshipTypeService,
    private bankDetailsService: BankDetailsService,
    private accountDetailsService: AccountDetailsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initializeVariables();
    this.validateUserAuthorization();
    this.fetchTestNumber().subscribe(testNumber => this.testNumber = testNumber);
    this.getDocumentReceivedStatus().subscribe(status => this.documentReceivedStatus = status);
    this.partnerTypeService.getPartnerTypes().subscribe(types => this.partnerTypes = types);
    this.relationshipTypeService.getRelationshipTypes().subscribe(types => this.relationshipTypes = types);
  }

  initializeVariables(): void {
    this.testNumber = '';
    this.documentReceivedStatus = '';
    this.imageDetails = new ImageDetailsModel();
    this.proposalDetails = new ProposalDetailsModel();
    this.comments = [];
    this.searchResults = [];
    this.partnerTypes = [];
    this.relationshipTypes = [];
    this.bankDetails = new BankDetailsModel();
    this.accountDetails = new AccountDetailsModel();
  }

  validateUserAuthorization(): void {
    this.validationService.validateFlagsAndStatuses('applicationNo').subscribe(
      validationResults => this.handleValidationResults(validationResults),
      error => console.error('Error validating user authorization', error)
    );
  }

  handleValidationResults(validationResults: any): void {
    // Handle validation results and set visibility of fields accordingly
  }

  fetchTestNumber(): Observable<string> {
    return this.validationService.fetchTestNumber();
  }

  handleDocumentReceivedStatus(status: string): Observable<any> {
    return this.validationService.saveDocumentStatus(status);
  }

  getDocumentReceivedStatus(): Observable<string> {
    return this.validationService.fetchDocumentStatus();
  }

  handleImageDetails(imageDetails: ImageDetailsModel): Observable<ImageDetailsModel> {
    return this.imageDetailsService.saveImageDetails(imageDetails);
  }

  handleCheckboxChange(event: Event): void {
    // Handle checkbox change and enable/disable buttons accordingly
  }

  isButtonEnabled(buttonName: string): boolean {
    // Return whether a specific button should be enabled based on the checkbox state
    return false;
  }

  submitProposal(): void {
    this.proposalDetailsService.submitProposal(this.proposalDetails).subscribe(
      response => console.log('Proposal submitted successfully', response),
      error => console.error('Error submitting proposal', error)
    );
  }

  exitForm(): void {
    this.router.navigate(['/previous-page']);
  }

  viewDocuments(): void {
    // Open a modal or navigate to a page to view related documents
  }

  openProposalForm(): void {
    // Open the proposal form in an image viewer
  }

  downloadKycDocuments(): void {
    // Trigger a download for the KYC documents
  }

  onExitClick(): Promise<void> {
    return this.router.navigate(['/previous-page']).catch(error => console.error('Error navigating away', error));
  }

  onDropdownChange(event: Event): void {
    // Handle the change event for the dropdown field and update the component state accordingly
  }

  onProposalFormButtonClick(): void {
    this.validationService.generateDocumentViewerUrl('applicationNumber').subscribe(
      url => window.open(url, '_blank'),
      error => console.error('Error generating document viewer URL', error)
    );
  }

  downloadKycDoc(): void {
    // Retrieve the application number from the current record
    const applicationNumber = 'currentApplicationNumber';
    // Fetch the contract ID associated with the proposal number
    const contractId = 'contractId';
    // Retrieve personal details
    const personalDetails = {
      TAX_ID: 'taxId',
      DATE_OF_BIRTH: '01-01-1990',
      SEX: 'M',
      FIRST_NAME: 'John',
      MIDDLE_NAME: 'A',
      SURNAME: 'Doe'
    };
    // Construct a JSON string with the retrieved details
    const jsonString = JSON.stringify(personalDetails);
    // Send the JSON string to a predefined URL for KYC validation
    this.kycValidationService.downloadKycDoc(jsonString).subscribe(
      response => console.log('KYC validation response', response),
      error => console.error('Error downloading KYC document', error)
    );
  }

  validateTeleVideoMerSelection(): void {
    this.validationService.checkDocumentVerificationStatus().subscribe(
      isVerified => {
        if (!isVerified) {
          console.warn('Please verify the document before selecting Yes or No option');
          // Reset the selection to null
        }
      },
      error => console.error('Error checking document verification status', error)
    );
  }

  addComment(comment: string): Observable<any> {
    return this.underwriterCommentService.addComment(comment);
  }

  refreshComments(): Observable<UnderwriterComment[]> {
    return this.underwriterCommentService.getComments();
  }

  handleDoubleClick(event: MouseEvent): void {
    // Open the editor window for comments
  }

  saveComments(comments: string): void {
    // Save the edited comments
  }

  onAddCommentsButtonClick(): void {
    this.underwriterCommentService.captureContractAndUserId();
    // Navigate to the add-comment component
  }

  onRejectButtonClick(): void {
    // Handle the 'Reject' button click event
  }

  handleSearch(fromDate: string, toDate: string, applicationNumber: string, partnerType: string, status: string): Observable<DeqcSearchModel[]> {
    return this.validationService.searchRecords({ fromDate, toDate, applicationNumber, partnerType, status });
  }

  handleClear(): void {
    // Reset all input fields to their default state
  }

  handleSave(currentState: any): Observable<any> {
    return this.validationService.saveState(currentState);
  }

  handleViewDocs(recordId: string): Observable<DocumentModel[]> {
    return this.documentService.viewDocuments(recordId);
  }

  handleUploadDocs(recordId: string, documents: File[]): Observable<any> {
    return this.documentService.uploadDocuments(recordId, documents);
  }

  handleQcEdit(recordId: string): Observable<any> {
    return this.validationService.editRecord(recordId);
  }

  handleUwComments(recordId: string): Observable<UnderwriterComment[]> {
    return this.underwriterCommentService.getComments(recordId);
  }

  handleReject(recordId: string): Observable<any> {
    return this.rejectService.rejectRecord(recordId);
  }

  handleOk(reason: string): void {
    // Confirm the reason for rejection
  }

  validateIFSCCode(ifscCode: string): Observable<ValidationDTO> {
    return this.validationService.validateIFSCCode(ifscCode);
  }

  saveAccountDetails(accountDetails: AccountDetailsModel): Observable<any> {
    return this.accountDetailsService.saveAccountDetails(accountDetails);
  }

  onRelationChange(event: Event): void {
    // Handle the selection of an option from the dropdown and save the selected option
  }

  clearForm(): void {
    // Reset all form fields to their default values without triggering any validation checks
  }

  onUwCommentsButtonPress(): void {
    this.displayReasonLinkSection();
    this.setFocusToReasonLink();
  }

  displayReasonLinkSection(): void {
    this.isReasonLinkVisible = true;
  }

  setFocusToReasonLink(): void {
    // Use Angular's ViewChild to get a reference to the "Reason Link" input field and call its focus method
  }

  onSearch(): void {
    // Ensure the search functionality works even if the partnerTypeField is not selected
  }

  onViewDocsClick(): void {
    // Handle the "View Docs" button click event
  }

  onCommentsFieldDoubleClick(event: MouseEvent): void {
    // Navigate to the comments view component
  }

  submitApplication(): Observable<any> {
    return this.validationService.validateDocuments().pipe(
      switchMap(isValid => {
        if (isValid) {
          return this.validationService.submitForm();
        } else {
          throw new Error('Validation failed');
        }
      })
    );
  }

  onIfscCodeChange(ifscCode: string): Observable<BankDetailsModel> {
    return this.validationService.getBankDetails(ifscCode);
  }

  displayBankDetails(bankDetails: BankDetailsModel): void {
    // Display the fetched bank details (bank name, branch, and MICR code) in the UI
  }

  onAccountTypeChange(accountType: string): void {
    this.bankDetailsService.fetchBankDetails(accountType).subscribe(
      bankDetails => this.displayBankDetails(bankDetails),
      error => this.clearAccountDetails()
    );
  }

  handleBypassBankDetailsChange(event: Event): void {
    // Handle the state of the "Bypass Bank Details" checkbox
  }

  clearAccountDetails(): void {
    // Clear all the fields in the account details block
  }

  onSameBankDtlsClick(): void {
    const accountNumber = this.accountDetails.accountNumber;
    const ifscCode = this.accountDetails.ifscCode;
    if (!this.validationService.validateAccountDetails(accountNumber, ifscCode)) {
      console.warn('Please enter the Account No and IFSC Code.!');
    } else {
      // Pass the Account Number, IFSC Code, form name, and IP part ID as parameters to another form that displays the same bank details
    }
  }

  validateToDate(fromDate: Date, toDate: Date): void {
    if (!this.validationService.validateDateDifference(fromDate, toDate)) {
      console.error('The date difference should not be greater than six months and To Date should be greater than From Date.');
      // Disable the search button and display an error message
    }
  }

  onStatusChange(event: Event): void {
    // Handle the change event for the status dropdown
  }

  submitComment(): void {
    const comment = 'userComment';
    if (!comment) {
      console.warn('Please enter a comment.');
      return;
    }
    this.underwriterCommentService.submitComment(comment).subscribe(
      response => console.log('Comment submitted successfully', response),
      error => console.error('Error submitting comment', error)
    );
  }

  onQcButtonClick(): void {
    const selectedCase = 'selectedCase';
    this.validationService.validateSelectedCase(selectedCase).subscribe(
      isValid => {
        if (isValid) {
          this.navigateToDeqcDisplay();
        } else {
          console.warn('Selected case is not allowed for DEQC');
        }
      },
      error => console.error('Error validating selected case', error)
    );
  }

  navigateToDeqcDisplay(): void {
    // Navigate to the DEQC display block
  }

  handleSearch(searchCriteria: SearchCriteriaModel): void {
    this.validationService.searchApplications(searchCriteria).subscribe(
      results => this.searchResults = results,
      error => console.error('Error fetching search results', error)
    );
  }

  onExitButtonClick(): void {
    this.closeForm();
  }

  closeForm(): void {
    // Logic to close the current form, such as navigating to a different route or closing a modal
  }

  validatePartnerType(): boolean {
    const partnerType = 'partnerType';
    if (!this.validationService.validatePartnerType(partnerType)) {
      console.warn('Please enter partner.');
      return false;
    }
    return true;
  }

  onViewPreviousPolicyClick(): void {
    this.validationService.checkAndCreateParamList().subscribe(
      paramList => this.validationService.callForm(paramList).subscribe(),
      error => console.error('Error creating parameter list', error)
    );
  }

  onPhNoPanLovChange(event: Event): void {
    const phNoPanCard = 'Y';
    const phNoPanLov = 'phNoPanLov';
    if (phNoPanCard === 'Y' && !this.validationService.validatePhNoPanLovChange(phNoPanCard, phNoPanLov)) {
      console.error('Please select one of the LOV for pan card not received flag.');
    }
  }

  checkPanVerificationStatus(): void {
    this.validationService.checkPanStatus().subscribe(
      isVerified => {
        if (!isVerified) {
          console.error('PAN card has been received but not verified. Please raise the FRAR again.');
        }
      },
      error => console.error('Error checking PAN verification status', error)
    );
  }
}
