import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanDetailsComponent } from './pan-details.component';

const routes: Routes = [
  { path: '', component: PanDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PanDetailsRoutingModule { }
