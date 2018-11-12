export class Competition {
  id: number;
  name: string;
  matches: any[];
  numberOfPlayers: number;
  numberOfRounds: number;
  numberOfMatchesInFirstRound: number;


  calculateMatchesInRound(round: number): number {
    if (round == 1) {
      return this.numberOfMatchesInFirstRound;
    }
    return Math.pow(2,this.numberOfRounds - round);
  }

  calculateMaxMatchesInRound(round: number): number {
    return Math.pow(2,this.numberOfRounds - round);
  }

  getMatchByPosition(round: number, position: number): any {
    console.log(this.matches);
    return this.matches['['+round+'/'+position+']']
  }

  isDoubleMatch(round: number, position: number): boolean {
    return (position % 2 == 1 && position < this.calculateMatchesInRound(round));
  }

  isWithoutMiddleBar(round: number, position: number): boolean {
    return (round == 1) || (round == 2 && position > (this.numberOfMatchesInFirstRound+1)/2);
  }

}
