package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.Winner;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;


public interface VictoryConditionsChecker {

    Winner determineWinner(ResultRegistrationForm resultRegistrationForm, VictoryConditions victoryConditions);

}
