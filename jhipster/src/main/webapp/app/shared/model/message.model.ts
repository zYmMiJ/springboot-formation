import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IMessage {
  id?: number;
  title?: string;
  content?: string;
  creationDate?: Moment;
  userMessages?: IUser;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public title?: string,
    public content?: string,
    public creationDate?: Moment,
    public userMessages?: IUser
  ) {}
}
