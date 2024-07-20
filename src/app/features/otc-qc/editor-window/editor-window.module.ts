import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EditorWindowComponent } from './editor-window.component';

@NgModule({
  declarations: [
    EditorWindowComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    EditorWindowComponent
  ]
})
export class EditorWindowModule { }
