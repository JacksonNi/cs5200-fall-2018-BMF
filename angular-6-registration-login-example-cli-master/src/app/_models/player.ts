import {User} from './user';

export class Player extends User {
  id: number;
  gender: string;
  nationality: string;
  height: number;
  weight: number;
  jerserNumber: number;
  preferredFoot: string;
}

