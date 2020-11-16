import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Exercice6SharedModule } from 'app/shared/shared.module';
import { Exercice6CoreModule } from 'app/core/core.module';
import { Exercice6AppRoutingModule } from './app-routing.module';
import { Exercice6HomeModule } from './home/home.module';
import { Exercice6EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Exercice6SharedModule,
    Exercice6CoreModule,
    Exercice6HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Exercice6EntityModule,
    Exercice6AppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Exercice6AppModule {}
