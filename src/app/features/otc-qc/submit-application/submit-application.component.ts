import { Component } from '@angular/core';
import { ValidationService } from 'src/app/core/services/validation.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-submit-application',
  templateUrl: './submit-application.component.html',
  styleUrls: ['./submit-application.component.css']
})
export class SubmitApplicationComponent {
  constructor(private validationService: ValidationService, private logger: NGXLogger) {}

  submitApplication(): Observable<any> {
    this.logger.info('Starting application submission process.');
    return this.validationService.validateDocuments().pipe(
      catchError(error => {
        this.logger.error('Document validation failed', error);
        throw error;
      }),
      switchMap(isValid => {
        if (isValid) {
          this.logger.info('All documents validated successfully. Proceeding to submit the application.');
          return this.validationService.submitForm().pipe(
            catchError(error => {
              this.logger.error('Application submission failed', error);
              throw error;
            })
          );
        } else {
          this.logger.warn('Document validation failed. Cannot proceed with application submission.');
          throw new Error('Document validation failed');
        }
      })
    );
  }
}
