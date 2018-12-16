package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;


public interface MatchFormValidator {

    void isValid(MatchForm matchForm, CompetitionGameSettings gameSettings);

}
