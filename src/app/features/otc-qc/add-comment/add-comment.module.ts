import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddCommentComponent } from './add-comment.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxLoggerModule } from 'ngx-logger';

@NgModule({
  declarations: [AddCommentComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxLoggerModule.forRoot({
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    })
  ],
  exports: [AddCommentComponent]
})
export class AddCommentModule { }
