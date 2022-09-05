import {Status} from "./Status";

export class Todo {

  id?: number
  status?: Status;
  title!: string;
  description!: string;
  deadline?: Date;
  estimatedTimeInSeconds?:number;
  creationDate?: Date;

}
