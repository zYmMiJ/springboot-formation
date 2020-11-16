import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommentary } from 'app/shared/model/commentary.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CommentaryService } from './commentary.service';
import { CommentaryDeleteDialogComponent } from './commentary-delete-dialog.component';

@Component({
  selector: 'jhi-commentary',
  templateUrl: './commentary.component.html',
})
export class CommentaryComponent implements OnInit, OnDestroy {
  commentaries: ICommentary[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected commentaryService: CommentaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.commentaries = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.commentaryService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ICommentary[]>) => this.paginateCommentaries(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.commentaries = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCommentaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommentary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommentaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('commentaryListModification', () => this.reset());
  }

  delete(commentary: ICommentary): void {
    const modalRef = this.modalService.open(CommentaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.commentary = commentary;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCommentaries(data: ICommentary[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.commentaries.push(data[i]);
      }
    }
  }
}
