package pl.sixpinetrees.tournament.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionTest {

    @Test
    public void shouldAssignPlayersToMatchesInCompetition() {

        //given
        List<Player> players = new ArrayList<>();

        players.add(new Player("Johnny", "Bravo"));
        players.add(new Player("Marty", "McFly"));
        players.add(new Player("Barney", "Stinson"));
        players.add(new Player("Sonny", "Crockett"));
        players.add(new Player("Lucy", "Prince"));

        //when
        Competition competition = new Competition("test", players);

        //then
        assertThat(competition.getNumberOfPlayers()).isEqualTo(5);
        assertThat(competition.getNumberOfRounds()).isEqualTo(3);
        assertThat(competition.getMatches().size()).isEqualTo(4);

        assertThat(competition.getMatches().get(new BracketPosition(1,1)).getName()).isEqualTo("1/4-final no. 1");
        assertThat(competition.getMatches().get(new BracketPosition(2,1)).getName()).isEqualTo("1/2-final no. 1");
        assertThat(competition.getMatches().get(new BracketPosition(2,2)).getName()).isEqualTo("1/2-final no. 2");
        assertThat(competition.getMatches().get(new BracketPosition(3,1)).getName()).isEqualTo("1/1-final no. 1");

        assertThat(competition.getMatches().get(new BracketPosition(1,1)).getPlayer1()).isEqualTo(players.get(0));
        assertThat(competition.getMatches().get(new BracketPosition(1,1)).getPlayer2()).isEqualTo(players.get(1));
        assertThat(competition.getMatches().get(new BracketPosition(2,1)).getPlayer1()).isNull();
        assertThat(competition.getMatches().get(new BracketPosition(2,1)).getPlayer2()).isEqualTo(players.get(2));
        assertThat(competition.getMatches().get(new BracketPosition(2,2)).getPlayer1()).isEqualTo(players.get(3));
        assertThat(competition.getMatches().get(new BracketPosition(2,2)).getPlayer2()).isEqualTo(players.get(4));
        assertThat(competition.getMatches().get(new BracketPosition(3,1)).getPlayer1()).isNull();
        assertThat(competition.getMatches().get(new BracketPosition(3,1)).getPlayer1()).isNull();
    }

}