import { element, by, ElementFinder } from 'protractor';

export class CommentaryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-commentary div table .btn-danger'));
  title = element.all(by.css('jhi-commentary div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class CommentaryUpdatePage {
  pageTitle = element(by.id('jhi-commentary-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  textInput = element(by.id('field_text'));
  creationDateInput = element(by.id('field_creationDate'));

  messageCommentariesSelect = element(by.id('field_messageCommentaries'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTextInput(text: string): Promise<void> {
    await this.textInput.sendKeys(text);
  }

  async getTextInput(): Promise<string> {
    return await this.textInput.getAttribute('value');
  }

  async setCreationDateInput(creationDate: string): Promise<void> {
    await this.creationDateInput.sendKeys(creationDate);
  }

  async getCreationDateInput(): Promise<string> {
    return await this.creationDateInput.getAttribute('value');
  }

  async messageCommentariesSelectLastOption(): Promise<void> {
    await this.messageCommentariesSelect.all(by.tagName('option')).last().click();
  }

  async messageCommentariesSelectOption(option: string): Promise<void> {
    await this.messageCommentariesSelect.sendKeys(option);
  }

  getMessageCommentariesSelect(): ElementFinder {
    return this.messageCommentariesSelect;
  }

  async getMessageCommentariesSelectedOption(): Promise<string> {
    return await this.messageCommentariesSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class CommentaryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-commentary-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-commentary'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
