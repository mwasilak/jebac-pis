package pl.sixpinetrees.tournament.service;

import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.Winner;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

import java.util.ArrayList;
import java.util.List;


@Component
public class ResultRegistrationFormValidatorImpl implements ResultRegistrationFormValidator {

    public ResultRegistrationFormValidatorImpl() {
    }

    @Override
    public Winner isValid(ResultRegistrationForm resultRegistrationForm, VictoryConditions victoryConditions) {

        List<String> errorList = new ArrayList<>();

        if(resultRegistrationForm == null) {
            errorList.add("No match form provided.");
            throw new ServiceValidationException(errorList);
        }

        Integer player1Wins = 0;
        Integer player2Wins = 0;
        Integer index = 0;

        for(GameRow game : resultRegistrationForm.getGames()) {
            index += 1;
            if ((game.getScorePlayer1() > (game.getScorePlayer2() + 1)) && (game.getScorePlayer1() == victoryConditions.getNumberOfPointsToWin())) {
                player1Wins += 1;
            } else if ((game.getScorePlayer1() == (game.getScorePlayer2() + 2)) && (game.getScorePlayer1() > victoryConditions.getNumberOfPointsToWin())) {
                player1Wins += 1;
            } else if ((game.getScorePlayer2() > (game.getScorePlayer1() + 1)) && (game.getScorePlayer2() == victoryConditions.getNumberOfPointsToWin())) {
                player2Wins += 1;
            } else if ((game.getScorePlayer2() == (game.getScorePlayer1() + 2)) && (game.getScorePlayer2() > victoryConditions.getNumberOfPointsToWin())) {
                player2Wins += 1;
            } else {
                errorList.add("Game no. " + index + " has incorrect score.");
            }
        }
        if(!errorList.isEmpty()) {
            throw new ServiceValidationException(errorList);
        }

        if(((player1Wins>player2Wins && player1Wins != victoryConditions.getNumberOfWinsRequired()) ||
                (player2Wins>player1Wins && player2Wins != victoryConditions.getNumberOfWinsRequired())) ||
                (player1Wins==player2Wins) ) {
            errorList.add("Incorrect number of games won.");
            throw new ServiceValidationException(errorList);
        }
        if(player1Wins > player2Wins) {
            return Winner.PLAYER1;
        }
        return Winner.PLAYER2;

    }
}
