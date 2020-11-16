import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CommentaryComponentsPage, CommentaryDeleteDialog, CommentaryUpdatePage } from './commentary.page-object';

const expect = chai.expect;

describe('Commentary e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let commentaryComponentsPage: CommentaryComponentsPage;
  let commentaryUpdatePage: CommentaryUpdatePage;
  let commentaryDeleteDialog: CommentaryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Commentaries', async () => {
    await navBarPage.goToEntity('commentary');
    commentaryComponentsPage = new CommentaryComponentsPage();
    await browser.wait(ec.visibilityOf(commentaryComponentsPage.title), 5000);
    expect(await commentaryComponentsPage.getTitle()).to.eq('exercice6App.commentary.home.title');
    await browser.wait(ec.or(ec.visibilityOf(commentaryComponentsPage.entities), ec.visibilityOf(commentaryComponentsPage.noResult)), 1000);
  });

  it('should load create Commentary page', async () => {
    await commentaryComponentsPage.clickOnCreateButton();
    commentaryUpdatePage = new CommentaryUpdatePage();
    expect(await commentaryUpdatePage.getPageTitle()).to.eq('exercice6App.commentary.home.createOrEditLabel');
    await commentaryUpdatePage.cancel();
  });

  it('should create and save Commentaries', async () => {
    const nbButtonsBeforeCreate = await commentaryComponentsPage.countDeleteButtons();

    await commentaryComponentsPage.clickOnCreateButton();

    await promise.all([
      commentaryUpdatePage.setTextInput('text'),
      commentaryUpdatePage.setCreationDateInput('2000-12-31'),
      commentaryUpdatePage.messageCommentariesSelectLastOption(),
    ]);

    expect(await commentaryUpdatePage.getTextInput()).to.eq('text', 'Expected Text value to be equals to text');
    expect(await commentaryUpdatePage.getCreationDateInput()).to.eq('2000-12-31', 'Expected creationDate value to be equals to 2000-12-31');

    await commentaryUpdatePage.save();
    expect(await commentaryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await commentaryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Commentary', async () => {
    const nbButtonsBeforeDelete = await commentaryComponentsPage.countDeleteButtons();
    await commentaryComponentsPage.clickOnLastDeleteButton();

    commentaryDeleteDialog = new CommentaryDeleteDialog();
    expect(await commentaryDeleteDialog.getDialogTitle()).to.eq('exercice6App.commentary.delete.question');
    await commentaryDeleteDialog.clickOnConfirmButton();

    expect(await commentaryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
