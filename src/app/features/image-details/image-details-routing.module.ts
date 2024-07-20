import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ImageDetailsComponent } from './image-details.component';
import { ApplicationDetailsComponent } from '../application-details/application-details.component';

const routes: Routes = [
  { path: '', component: ImageDetailsComponent },
  { path: 'application-details', component: ApplicationDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImageDetailsRoutingModule { }
