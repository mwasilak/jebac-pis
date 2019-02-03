package pl.sixpinetrees.tournament.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BracketMatchFactoryTest {

    @Autowired
    private BracketMatchFactory bracketMatchFactory;


    @Test
    public void shouldGenerateMatches() throws Exception {
        //given
        List<Player> players = new ArrayList<>();

        players.add(new Player("Johnny", "Bravo"));
        players.add(new Player("Marty", "McFly"));
        players.add(new Player("Barney", "Stinson"));
        players.add(new Player("Sonny", "Crockett"));
        players.add(new Player("Lucy", "Prince"));

        Competition competition = new Competition("test", players.size(), 3, 11);
        competition.setId(101L);

        //when
        Map<BracketPosition, Match> matches = bracketMatchFactory.generateMatches(competition, players);

        //then
        assertThat(matches.size()).isEqualTo(4);

        assertThat(matches.get(new BracketPosition(1,1)).getCompetitionId()).isEqualTo(101);
        assertThat(matches.get(new BracketPosition(2,1)).getCompetitionId()).isEqualTo(101);
        assertThat(matches.get(new BracketPosition(2,2)).getCompetitionId()).isEqualTo(101);
        assertThat(matches.get(new BracketPosition(3,1)).getCompetitionId()).isEqualTo(101);

        assertThat(matches.get(new BracketPosition(1,1)).getName()).isEqualTo("1/4-final no. 1");
        assertThat(matches.get(new BracketPosition(2,1)).getName()).isEqualTo("1/2-final no. 1");
        assertThat(matches.get(new BracketPosition(2,2)).getName()).isEqualTo("1/2-final no. 2");
        assertThat(matches.get(new BracketPosition(3,1)).getName()).isEqualTo("1/1-final no. 1");

        assertThat(matches.get(new BracketPosition(1,1)).getPlayer1()).isEqualTo(players.get(0));
        assertThat(matches.get(new BracketPosition(1,1)).getPlayer2()).isEqualTo(players.get(1));
        assertThat(matches.get(new BracketPosition(2,1)).getPlayer1()).isNull();
        assertThat(matches.get(new BracketPosition(2,1)).getPlayer2()).isEqualTo(players.get(2));
        assertThat(matches.get(new BracketPosition(2,2)).getPlayer1()).isEqualTo(players.get(3));
        assertThat(matches.get(new BracketPosition(2,2)).getPlayer2()).isEqualTo(players.get(4));
        assertThat(matches.get(new BracketPosition(3,1)).getPlayer1()).isNull();
        assertThat(matches.get(new BracketPosition(3,1)).getPlayer1()).isNull();
    }
}