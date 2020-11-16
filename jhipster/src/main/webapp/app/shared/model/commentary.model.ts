import { Moment } from 'moment';

export interface ICommentary {
  id?: number;
  text?: string;
  creationDate?: Moment;
  messageCommentariesId?: number;
}

export class Commentary implements ICommentary {
  constructor(public id?: number, public text?: string, public creationDate?: Moment, public messageCommentariesId?: number) {}
}
