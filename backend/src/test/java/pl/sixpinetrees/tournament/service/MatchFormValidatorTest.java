package pl.sixpinetrees.tournament.service;

import org.junit.Assert;
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
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MatchFormValidatorTest {

    @Autowired
    MatchFormValidator matchFormValidator;

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

        MatchForm matchForm = new MatchForm();
        matchForm.setGames(gameList);

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        matchFormValidator.isValid(matchForm, competitionGameSettings);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenNoMatchFormProvided() throws Exception {
        //given
        MatchForm matchForm = null;

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        matchFormValidator.isValid(matchForm, competitionGameSettings);

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

        MatchForm matchForm = new MatchForm();
        matchForm.setGames(gameList);

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        matchFormValidator.isValid(matchForm, competitionGameSettings);

        //then
    }

    @Test(expected = ServiceValidationException.class)
    public void shouldFailWhenMatchFormWithWrongGameNumberProvided() throws Exception {
        //given
        List<GameRow> gameList = new ArrayList<>();
        gameList.add(new GameRow(11, 2));
        gameList.add(new GameRow(9, 11));
        gameList.add(new GameRow(1, 13));
        gameList.add(new GameRow(12, 10));

        MatchForm matchForm = new MatchForm();
        matchForm.setGames(gameList);

        CompetitionGameSettings competitionGameSettings = factory.createProjection(CompetitionGameSettings.class);
        competitionGameSettings.setNumberOfPointsToWin(11);
        competitionGameSettings.setNumberOfWinsRequired(3);

        //when
        matchFormValidator.isValid(matchForm, competitionGameSettings);

        //then
    }
}