import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PanDetailsComponent } from './pan-details.component';
import { PanDetailsRoutingModule } from './pan-details-routing.module';
import { PanDetailsService } from 'src/app/core/services/pan-details.service';

@NgModule({
  declarations: [
    PanDetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PanDetailsRoutingModule
  ],
  providers: [
    PanDetailsService
  ],
  exports: [
    PanDetailsComponent
  ]
})
export class PanDetailsModule { }