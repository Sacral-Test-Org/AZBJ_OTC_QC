import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentInfoComponent } from './document-info.component';

@NgModule({
  declarations: [
    DocumentInfoComponent
  ],
  exports: [
    DocumentInfoComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DocumentInfoModule { }
