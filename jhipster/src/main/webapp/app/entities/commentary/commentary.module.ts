import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Exercice6SharedModule } from 'app/shared/shared.module';
import { CommentaryComponent } from './commentary.component';
import { CommentaryDetailComponent } from './commentary-detail.component';
import { CommentaryUpdateComponent } from './commentary-update.component';
import { CommentaryDeleteDialogComponent } from './commentary-delete-dialog.component';
import { commentaryRoute } from './commentary.route';

@NgModule({
  imports: [Exercice6SharedModule, RouterModule.forChild(commentaryRoute)],
  declarations: [CommentaryComponent, CommentaryDetailComponent, CommentaryUpdateComponent, CommentaryDeleteDialogComponent],
  entryComponents: [CommentaryDeleteDialogComponent],
})
export class Exercice6CommentaryModule {}
