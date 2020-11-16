import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.Exercice6MessageModule),
      },
      {
        path: 'commentary',
        loadChildren: () => import('./commentary/commentary.module').then(m => m.Exercice6CommentaryModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Exercice6EntityModule {}
