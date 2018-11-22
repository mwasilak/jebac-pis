package pl.sixpinetrees.tournament.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {


    @Test
    public void calculateBracketKey1() throws Exception {

        //given
        Match match = new Match("x", 3, 2);

        //when
        String result = match.calculateBracketKey();

        //then
        assertThat(result).isEqualTo("3/12");
    }


    @Test
    public void calculateBracketKey2() throws Exception {

        //given
        Match match = new Match("x", 1, 16);

        //when
        String result = match.calculateBracketKey();

        //then
        assertThat(result).isEqualTo("1/31");
    }
}