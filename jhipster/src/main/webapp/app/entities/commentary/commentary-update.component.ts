import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommentary, Commentary } from 'app/shared/model/commentary.model';
import { CommentaryService } from './commentary.service';
import { IMessage } from 'app/shared/model/message.model';
import { MessageService } from 'app/entities/message/message.service';

@Component({
  selector: 'jhi-commentary-update',
  templateUrl: './commentary-update.component.html',
})
export class CommentaryUpdateComponent implements OnInit {
  isSaving = false;
  messages: IMessage[] = [];
  creationDateDp: any;

  editForm = this.fb.group({
    id: [],
    text: [null, [Validators.required, Validators.minLength(10), Validators.maxLength(50)]],
    creationDate: [],
    messageCommentariesId: [],
  });

  constructor(
    protected commentaryService: CommentaryService,
    protected messageService: MessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commentary }) => {
      this.updateForm(commentary);

      this.messageService.query().subscribe((res: HttpResponse<IMessage[]>) => (this.messages = res.body || []));
    });
  }

  updateForm(commentary: ICommentary): void {
    this.editForm.patchValue({
      id: commentary.id,
      text: commentary.text,
      creationDate: commentary.creationDate,
      messageCommentariesId: commentary.messageCommentariesId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commentary = this.createFromForm();
    if (commentary.id !== undefined) {
      this.subscribeToSaveResponse(this.commentaryService.update(commentary));
    } else {
      this.subscribeToSaveResponse(this.commentaryService.create(commentary));
    }
  }

  private createFromForm(): ICommentary {
    return {
      ...new Commentary(),
      id: this.editForm.get(['id'])!.value,
      text: this.editForm.get(['text'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value,
      messageCommentariesId: this.editForm.get(['messageCommentariesId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommentary>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IMessage): any {
    return item.id;
  }
}
