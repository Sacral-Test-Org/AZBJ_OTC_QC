import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PanDetailsService } from 'src/app/core/services/pan-details.service';
import { ValidationService } from 'src/app/core/services/validation.service';
import { PanDetailsModel } from 'src/app/shared/models/pan-details.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-pan-details',
  templateUrl: './pan-details.component.html',
  styleUrls: ['./pan-details.component.css']
})
export class PanDetailsComponent implements OnInit {
  panForm: FormGroup;
  isPanCardAvailable: boolean = true;
  panDetails: PanDetailsModel[] = [];

  constructor(
    private fb: FormBuilder,
    private panDetailsService: PanDetailsService,
    private validationService: ValidationService
  ) { }

  ngOnInit(): void {
    this.panForm = this.fb.group({
      panNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      panStatus: ['', Validators.required],
      nameMatch: ['', Validators.required],
      dobMatch: ['', Validators.required],
      address: ['', Validators.required],
      firstName: ['', Validators.required],
      middleName: [''],
      lastName: ['', Validators.required],
      dob: ['', Validators.required],
      partnerType: ['', Validators.required],
      panNotAvailable: [false],
      rrbBankAccount: [{ value: false, disabled: true }]
    });

    this.panForm.get('panNotAvailable').valueChanges.subscribe((value) => {
      this.onPanCardAvailabilityChange(value);
    });

    this.fetchPanDetails();
  }

  fetchPanDetails(): void {
    this.panDetailsService.getPanDetails().subscribe((details: PanDetailsModel[]) => {
      this.panDetails = details;
    });
  }

  onSubmit(): void {
    if (this.panForm.valid) {
      const panDetails: PanDetailsModel = this.panForm.value;
      this.validationService.validatePanDetails(panDetails).subscribe((validationResult: boolean) => {
        if (validationResult) {
          alert('PAN details are valid.');
        } else {
          alert('PAN details are invalid.');
        }
      });
    } else {
      this.promptUserForDetails();
    }
  }

  saveBankDetails(): void {
    const bankDetails = this.panForm.value;
    this.panDetailsService.saveBankDetails(bankDetails).subscribe(() => {
      alert('Bank details saved successfully.');
    });
  }

  validatePanCard(): void {
    const panCardNumber = this.panForm.get('panNumber').value;
    this.validationService.validatePanCard(panCardNumber).subscribe((details: PanDetailsModel) => {
      if (details) {
        this.panForm.patchValue({
          firstName: details.firstName,
          middleName: details.middleName,
          lastName: details.lastName,
          dob: details.dob
        });
      } else {
        alert('Invalid PAN card number.');
      }
    });
  }

  onPanCardAvailabilityChange(event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked) {
      this.isPanCardAvailable = false;
      this.panForm.get('panNumber').disable();
      this.panForm.get('firstName').reset();
      this.panForm.get('middleName').reset();
      this.panForm.get('lastName').reset();
      this.panForm.get('dob').reset();
      this.panForm.get('panStatus').reset();
    } else {
      this.isPanCardAvailable = true;
      this.panForm.get('panNumber').enable();
    }
  }

  promptUserForDetails(): void {
    if (!this.panForm.get('panNumber').value) {
      alert('Please enter the PAN card number.');
    }
    if (!this.panForm.get('partnerType').value) {
      alert('Please select the partner type.');
    }
    if (!this.panForm.get('firstName').value) {
      alert('Please enter the first name.');
    }
    if (!this.panForm.get('dob').value) {
      alert('Please enter the date of birth.');
    }
  }

  calculateAge(dob: Date): number {
    const diff = Date.now() - new Date(dob).getTime();
    const ageDt = new Date(diff);
    return Math.abs(ageDt.getUTCFullYear() - 1970);
  }
}
