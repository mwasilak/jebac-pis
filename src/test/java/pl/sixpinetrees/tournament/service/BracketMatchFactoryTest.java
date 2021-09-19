package pl.sixpinetrees.tournament.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BracketMatchFactoryTest {

    @Autowired
    private BracketMatchFactory bracketMatchFactory;


    @Test
    public void shouldGenerateMatches() throws Exception {
        //given
        List<Long> playerIds = new ArrayList<>();

        playerIds.add(101L);
        playerIds.add(1500100900L);
        playerIds.add(25L);
        playerIds.add(100L);
        playerIds.add(300L);

        Competition competition = new Competition("test", playerIds.size(), 3, 11);
        competition.setId(101L);

        //when
        Map<BracketPosition, Match> matches = bracketMatchFactory.generateMatches(competition, playerIds);

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

        assertThat(matches.get(new BracketPosition(1,1)).getPlayer1Id()).isEqualTo(playerIds.get(0));
        assertThat(matches.get(new BracketPosition(1,1)).getPlayer2Id()).isEqualTo(playerIds.get(1));
        assertThat(matches.get(new BracketPosition(2,1)).getPlayer1Id()).isNull();
        assertThat(matches.get(new BracketPosition(2,1)).getPlayer2Id()).isEqualTo(playerIds.get(2));
        assertThat(matches.get(new BracketPosition(2,2)).getPlayer1Id()).isEqualTo(playerIds.get(3));
        assertThat(matches.get(new BracketPosition(2,2)).getPlayer2Id()).isEqualTo(playerIds.get(4));
        assertThat(matches.get(new BracketPosition(3,1)).getPlayer1Id()).isNull();
        assertThat(matches.get(new BracketPosition(3,1)).getPlayer1Id()).isNull();
    }
}