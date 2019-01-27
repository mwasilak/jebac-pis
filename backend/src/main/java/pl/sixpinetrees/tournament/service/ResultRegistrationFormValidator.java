package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.VictoryConditions;
import pl.sixpinetrees.tournament.domain.Winner;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;


public interface ResultRegistrationFormValidator {

    Winner isValid(ResultRegistrationForm resultRegistrationForm, VictoryConditions victoryConditions);

}
