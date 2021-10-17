package pl.sixpinetrees.tournament.service;

import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.MatchWinner;
import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

import java.util.ArrayList;
import java.util.List;


@Component
public class VictoryConditionsCheckerImpl implements VictoryConditionsChecker {

    public VictoryConditionsCheckerImpl() {
    }

    @Override
    public MatchWinner determineWinner(ResultRegistrationForm resultRegistrationForm, VictoryConditions victoryConditions) {

        checkForm(resultRegistrationForm);

        MatchScore matchScore = new MatchScore(resultRegistrationForm.getGames(),
                victoryConditions.getNumberOfPointsToWin(), victoryConditions.getNumberOfWinsRequired());

        validateNumberOfWins(matchScore.player1Wins, matchScore.player2Wins,victoryConditions.getNumberOfWinsRequired() );

        if(matchScore.player1Wins > matchScore.player2Wins) {
            return MatchWinner.PLAYER1;
        }
        return MatchWinner.PLAYER2;

    }

    private void checkForm(ResultRegistrationForm resultRegistrationForm) {
        List<String> errorList = new ArrayList<>();

        if(resultRegistrationForm == null) {
            errorList.add("No match form provided.");
            throw new ServiceValidationException(errorList);
        }
    }

    private void validateNumberOfWins(Integer player1Wins, Integer player2Wins, Integer numberOfWinsRequired) {
        List<String> errorList = new ArrayList<>();
        if(((player1Wins > player2Wins && !player1Wins.equals(numberOfWinsRequired)) ||
                (player2Wins > player1Wins && !player2Wins.equals(numberOfWinsRequired))) ||
                (player1Wins.equals(player2Wins)) ) {
            errorList.add("Incorrect number of games won.");
            throw new ServiceValidationException(errorList);
        }
    }
}

class MatchScore {

    Integer player1Wins = 0;
    Integer player2Wins = 0;

    public MatchScore(List<GameRow> games, Integer numberOfPointsToWin, Integer numberOfWinsRequired) {
        List<String> errorList = new ArrayList<>();
        Integer index = 0;
        for(GameRow game : games) {
            index += 1;
            if(player1Wins >= numberOfWinsRequired || player2Wins >= numberOfWinsRequired) {
                    errorList.add("Game no. " + index + " occurs after victory conditions already satisfied.");
                    break;
            }
            if (((game.getScorePlayer1() > (game.getScorePlayer2() + 1)) && (game.getScorePlayer1().equals(numberOfPointsToWin))) ||
               ((game.getScorePlayer1() == (game.getScorePlayer2() + 2)) && (game.getScorePlayer1() >= numberOfPointsToWin))) {
                player1Wins += 1;
            } else if (((game.getScorePlayer2() > (game.getScorePlayer1() + 1)) && (game.getScorePlayer2().equals(numberOfPointsToWin))) ||
                ((game.getScorePlayer2() == (game.getScorePlayer1() + 2)) && (game.getScorePlayer2() >= numberOfPointsToWin))) {
                player2Wins += 1;
            } else {
                errorList.add("Game no. " + index + " has incorrect score.");
            }
        }
        if(!errorList.isEmpty()) {
            throw new ServiceValidationException(errorList);
        }
    }
}