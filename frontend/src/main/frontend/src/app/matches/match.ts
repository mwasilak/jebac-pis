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
  winner: string;

  status: string;
  player1: Player;
  player2: Player;

  initialize() {
    if(this.resultRegistrationTime !== null) {
      this.status = "concluded";
    } else if (this.player1 !== undefined && this.player2 !== undefined) {
      this.status = "pending";
    } else {
      this.status = "scheduled";
    }

  }

}
