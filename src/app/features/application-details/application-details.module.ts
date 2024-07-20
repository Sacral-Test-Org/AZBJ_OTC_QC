import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationDetailsComponent } from './application-details.component';

@NgModule({
  declarations: [
    ApplicationDetailsComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ApplicationDetailsComponent
  ]
})
export class ApplicationDetailsModule { }
