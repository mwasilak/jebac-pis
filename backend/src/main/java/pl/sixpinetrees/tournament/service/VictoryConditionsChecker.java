package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.MatchWinner;
import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;


public interface VictoryConditionsChecker {

    MatchWinner determineWinner(ResultRegistrationForm resultRegistrationForm, VictoryConditions victoryConditions);

}
