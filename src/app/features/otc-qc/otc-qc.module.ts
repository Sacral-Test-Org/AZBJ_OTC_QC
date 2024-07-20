import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OtcQcComponent } from './otc-qc.component';
import { DocumentInfoModule } from '../document-info/document-info.module';
import { PanDetailsModule } from '../pan-details/pan-details.module';
import { ApplicationDetailsModule } from '../application-details/application-details.module';
import { ProposalDetailsModule } from '../proposal-details/proposal-details.module';
import { UwCommentsModule } from '../uw-comments/uw-comments.module';
import { DeqcSearchComponent } from '../deqc-search/deqc-search.component';
import { DeqcSearchService } from '../../core/services/deqc-search.service';
import { AccountDetailsModule } from '../account-details/account-details.module';

@NgModule({
  declarations: [
    OtcQcComponent,
    DeqcSearchComponent
  ],
  imports: [
    CommonModule,
    DocumentInfoModule,
    PanDetailsModule,
    ApplicationDetailsModule,
    ProposalDetailsModule,
    UwCommentsModule,
    AccountDetailsModule
  ],
  providers: [
    DeqcSearchService
  ],
  exports: [
    OtcQcComponent
  ]
})
export class OtcQcModule { }