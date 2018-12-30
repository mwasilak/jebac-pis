package pl.sixpinetrees.tournament.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResultRegistrationFormValidatorTest {

    @Autowired
    ResultRegistrationFormValidator resultRegistrationFormValidator;

    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(expected = Test.None.class)
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

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, competitionGameSettings);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenNoMatchFormProvided() throws Exception {
        //given
        ResultRegistrationForm resultRegistrationForm = null;

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, competitionGameSettings);

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

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, competitionGameSettings);

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

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, competitionGameSettings);

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

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        resultRegistrationFormValidator.isValid(resultRegistrationForm, competitionGameSettings);

        //then
    }
}