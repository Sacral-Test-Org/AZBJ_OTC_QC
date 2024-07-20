import { Component, OnInit } from '@angular/core';
import { ApplicationDetailsModel } from 'src/app/shared/models/application-details.model';
import { ApplicationDetailsService } from 'src/app/shared/services/application-details.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-application-details',
  templateUrl: './application-details.component.html',
  styleUrls: ['./application-details.component.css']
})
export class ApplicationDetailsComponent implements OnInit {
  applicationDetails: ApplicationDetailsModel;

  constructor(private applicationDetailsService: ApplicationDetailsService, private router: Router) {}

  ngOnInit(): void {
    this.fetchApplicationDetails();
  }

  fetchApplicationDetails(): void {
    this.applicationDetailsService.getApplicationDetails().subscribe(
      (data: ApplicationDetailsModel) => {
        this.applicationDetails = data;
      },
      (error) => {
        console.error('Error fetching application details', error);
      }
    );
  }

  displayProposalDetails(): void {
    this.router.navigate(['/proposal-details', this.applicationDetails.proposalNumber]);
  }
}
