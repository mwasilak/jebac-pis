package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;


public interface ResultRegistrationFormValidator {

    void isValid(ResultRegistrationForm resultRegistrationForm, CompetitionGameSettings gameSettings);

}
