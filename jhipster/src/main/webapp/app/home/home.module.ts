import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Exercice6SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Exercice6SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class Exercice6HomeModule {}
