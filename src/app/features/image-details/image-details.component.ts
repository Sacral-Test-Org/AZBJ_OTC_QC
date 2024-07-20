import { Component } from '@angular/core';
import { ImageDetailsModel } from 'src/app/shared/models/image-details.model';
import { ImageDetailsService } from './image-details.service';
import { ImageService } from 'src/app/core/services/image.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-image-details',
  templateUrl: './image-details.component.html',
  styleUrls: ['./image-details.component.css']
})
export class ImageDetailsComponent {
  IMAGE_PATH: string;
  PROPOSAL_NO: string;
  APPLICATION_NO: string;
  IMAGE_NAME: string;
  IMAGE_SCAN_TIME: string;
  IMAGE_SIZE: number;
  NO_OF_PAGES: number;

  constructor(
    private imageDetailsService: ImageDetailsService,
    private imageService: ImageService,
    private router: Router
  ) {}

  saveImageDetails(imageDetails: ImageDetailsModel): Observable<ImageDetailsModel> {
    return this.imageDetailsService.saveImageDetails(imageDetails);
  }

  viewImage(imagePath: string): void {
    window.open(imagePath, '_blank');
  }

  hideImageDetails(): void {
    // Logic to hide image details in the UI
    document.getElementById('image-details-section').style.display = 'none';
  }

  onViewImageClick(): void {
    const proposalNumber = this.PROPOSAL_NO;
    const imageName = this.IMAGE_NAME;

    this.imageService.getImagePath(proposalNumber, imageName).subscribe(
      (imagePath) => {
        this.imageService.transferImage(imagePath).subscribe(
          () => {
            console.log(`Image transferred: ${imagePath}`);
            this.viewImage(imagePath);
          },
          (error) => {
            console.error('Error transferring image:', error);
            alert('Error transferring image. Please try again.');
          }
        );
      },
      (error) => {
        console.error('Error retrieving image path:', error);
        alert('Error retrieving image path. Please try again.');
      }
    );
  }

  onHideButtonClick(): void {
    this.router.navigate(['/proposal-details']);
  }
}
