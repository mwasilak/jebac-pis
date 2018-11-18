package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;

public interface CompetitionService {

    Long createCompetition(CompetitionForm competitionForm);

}
