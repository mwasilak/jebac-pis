export class Match {
  id: number;
  name: string;
  player1: any;
  player2: any;
  games: any[];
  bracketPosition: any;
  resultRegistrationTime: any;

  status: string;

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
