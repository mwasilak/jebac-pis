import {Game} from "./game";
import {Player} from "../players/player";

export class Match {
  id: number;
  name: string;
  player1Id: number;
  player2Id: number;
  games: Game[];
  bracketPosition: any;
  resultRegistrationTime: any;
  matchWinner: string;
  status: string;

  player1: Player;
  player2: Player;
}
