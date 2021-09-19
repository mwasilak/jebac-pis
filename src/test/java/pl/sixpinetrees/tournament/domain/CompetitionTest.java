package pl.sixpinetrees.tournament.domain;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionTest {

    @Test
    public void shouldSetCompetitionInternalVariables() throws Exception {
        //given

        //when
        Competition competition = new Competition("test", 5, 3, 11);

        //then
        assertThat(competition.getNumberOfPlayers()).isEqualTo(5);
        assertThat(competition.getNumberOfRounds()).isEqualTo(3);
    }

}