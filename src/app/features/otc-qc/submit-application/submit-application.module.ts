import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SubmitApplicationComponent } from './submit-application.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    SubmitApplicationComponent
  ],
  exports: [
    SubmitApplicationComponent
  ]
})
export class SubmitApplicationModule { }
