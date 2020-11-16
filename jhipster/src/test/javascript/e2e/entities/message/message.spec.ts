import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MessageComponentsPage, MessageDeleteDialog, MessageUpdatePage } from './message.page-object';

const expect = chai.expect;

describe('Message e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let messageComponentsPage: MessageComponentsPage;
  let messageUpdatePage: MessageUpdatePage;
  let messageDeleteDialog: MessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Messages', async () => {
    await navBarPage.goToEntity('message');
    messageComponentsPage = new MessageComponentsPage();
    await browser.wait(ec.visibilityOf(messageComponentsPage.title), 5000);
    expect(await messageComponentsPage.getTitle()).to.eq('exercice6App.message.home.title');
    await browser.wait(ec.or(ec.visibilityOf(messageComponentsPage.entities), ec.visibilityOf(messageComponentsPage.noResult)), 1000);
  });

  it('should load create Message page', async () => {
    await messageComponentsPage.clickOnCreateButton();
    messageUpdatePage = new MessageUpdatePage();
    expect(await messageUpdatePage.getPageTitle()).to.eq('exercice6App.message.home.createOrEditLabel');
    await messageUpdatePage.cancel();
  });

  it('should create and save Messages', async () => {
    const nbButtonsBeforeCreate = await messageComponentsPage.countDeleteButtons();

    await messageComponentsPage.clickOnCreateButton();

    await promise.all([
      messageUpdatePage.setTitleInput('title'),
      messageUpdatePage.setContentInput('content'),
      messageUpdatePage.setCreationDateInput('2000-12-31'),
      messageUpdatePage.userMessagesSelectLastOption(),
    ]);

    expect(await messageUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await messageUpdatePage.getContentInput()).to.eq('content', 'Expected Content value to be equals to content');
    expect(await messageUpdatePage.getCreationDateInput()).to.eq('2000-12-31', 'Expected creationDate value to be equals to 2000-12-31');

    await messageUpdatePage.save();
    expect(await messageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await messageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Message', async () => {
    const nbButtonsBeforeDelete = await messageComponentsPage.countDeleteButtons();
    await messageComponentsPage.clickOnLastDeleteButton();

    messageDeleteDialog = new MessageDeleteDialog();
    expect(await messageDeleteDialog.getDialogTitle()).to.eq('exercice6App.message.delete.question');
    await messageDeleteDialog.clickOnConfirmButton();

    expect(await messageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
