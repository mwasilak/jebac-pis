package pl.sixpinetrees.tournament.service;

import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class MatchFormValidatorImpl implements MatchFormValidator {

    public MatchFormValidatorImpl() {
    }

    @Override
    public void isValid(MatchForm matchForm, CompetitionGameSettings gameSettings) {

        List<String> errorList = new ArrayList<>();

        if(matchForm == null) {
            errorList.add("No match form provided.");
            throw new ServiceValidationException(errorList);
        }

        Integer player1Wins = 0;
        Integer player2Wins = 0;
        Integer index = 0;

        for(GameRow game : matchForm.getGames()) {
            index += 1;
            if ((game.getScorePlayer1() > (game.getScorePlayer2() + 1)) && (game.getScorePlayer1() == gameSettings.getNumberOfPointsToWin())) {
                player1Wins += 1;
            } else if ((game.getScorePlayer1() == (game.getScorePlayer2() + 2)) && (game.getScorePlayer1() > gameSettings.getNumberOfPointsToWin())) {
                player1Wins += 1;
            } else if ((game.getScorePlayer2() > (game.getScorePlayer1() + 1)) && (game.getScorePlayer2() == gameSettings.getNumberOfPointsToWin())) {
                player2Wins += 1;
            } else if ((game.getScorePlayer2() == (game.getScorePlayer1() + 2)) && (game.getScorePlayer2() > gameSettings.getNumberOfPointsToWin())) {
                player2Wins += 1;
            } else {
                errorList.add("Game no. " + index + " has incorrect score.");
            }
        }
        if(!errorList.isEmpty()) {
            throw new ServiceValidationException(errorList);
        }

        if((player1Wins>player2Wins && player1Wins != gameSettings.getNumberOfWinsRequired()) &&
            (player2Wins>player1Wins && player2Wins != gameSettings.getNumberOfWinsRequired())) {
            errorList.add("Incorrect number of games won.");
            throw new ServiceValidationException(errorList);
        }
    }
}
