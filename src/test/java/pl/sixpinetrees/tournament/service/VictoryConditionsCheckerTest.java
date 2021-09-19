package pl.sixpinetrees.tournament.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.MatchWinner;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VictoryConditionsCheckerTest {

    @Autowired
    VictoryConditionsChecker victoryConditionsChecker;

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
        MatchWinner matchWinner = victoryConditionsChecker.determineWinner(resultRegistrationForm, victoryConditions);

        //then
        assertThat(matchWinner).isEqualTo(MatchWinner.PLAYER1);
    }

    @Test()
    public void shouldFailWhenNoMatchFormProvided() throws Exception {
        //given
        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        Assertions.assertThrows(ServiceValidationException.class,
                () -> victoryConditionsChecker.determineWinner(null, victoryConditions));

        //then
    }

    @Test()
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
        Assertions.assertThrows(ServiceValidationException.class,
                () -> victoryConditionsChecker.determineWinner(resultRegistrationForm, victoryConditions));

        //then
    }

    @Test()
    public void shouldFailWhenMatchFormWithWrongGameResultsProvided2() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(12, 8));
        gameList.add(new GameRow(12, 8));
        gameList.add(new GameRow(12, 8));

        ResultRegistrationForm resultRegistrationForm = new ResultRegistrationForm();
        resultRegistrationForm.setGames(gameList);

        VictoryConditions victoryConditions = new VictoryConditions(3, 11);

        //when
        Assertions.assertThrows(ServiceValidationException.class,
                () -> victoryConditionsChecker.determineWinner(resultRegistrationForm, victoryConditions));

        //then
    }


    @Test()
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
        Assertions.assertThrows(ServiceValidationException.class,
                () -> victoryConditionsChecker.determineWinner(resultRegistrationForm, victoryConditions));

        //then
    }

    @Test()
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
        Assertions.assertThrows(ServiceValidationException.class,
                () -> victoryConditionsChecker.determineWinner(resultRegistrationForm, victoryConditions));

        //then
    }
}