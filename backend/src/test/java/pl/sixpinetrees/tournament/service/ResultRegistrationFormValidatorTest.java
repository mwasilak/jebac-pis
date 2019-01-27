package pl.sixpinetrees.tournament.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.Winner;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResultRegistrationFormValidatorTest {

    @Autowired
    ResultRegistrationFormValidator resultRegistrationFormValidator;

    @Test
    public void shouldSucceedWhenCorrectMatchFormProvided() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(9, 11));
        gameList.add(new GameRow(13, 11));
        gameList.add(new GameRow(10, 12));
        gameList.add(new GameRow(11, 0));

        ResultRegistrationForm resultRegistrationForm = new ResultRegistrationForm();
        resultRegistrationForm.setGames(gameList);

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        Winner winner = resultRegistrationFormValidator.isValid(resultRegistrationForm, victoryConditions);

        //then
        assertThat(winner).isEqualTo(Winner.PLAYER1);
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenNoMatchFormProvided() throws Exception {
        //given
        ResultRegistrationForm resultRegistrationForm = null;

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, victoryConditions);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenMatchFormWithWrongGameResultsProvided() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(10, 2));
        gameList.add(new GameRow(11, 11));
        gameList.add(new GameRow(13, 13));
        gameList.add(new GameRow(10, 28));
        gameList.add(new GameRow(0, 0));

        ResultRegistrationForm resultRegistrationForm = new ResultRegistrationForm();
        resultRegistrationForm.setGames(gameList);

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, victoryConditions);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenMatchFormWithWrongGameNumberProvided() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(9, 11));
        gameList.add(new GameRow(1, 11));
        gameList.add(new GameRow(12, 10));

        ResultRegistrationForm resultRegistrationForm = new ResultRegistrationForm();
        resultRegistrationForm.setGames(gameList);

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, victoryConditions);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenMatchFormWithSameNumberOfWinsProvided() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(2, 11));
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(2, 11));
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(2, 11));

        ResultRegistrationForm resultRegistrationForm = new ResultRegistrationForm();
        resultRegistrationForm.setGames(gameList);

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, victoryConditions);

        //then
    }
}