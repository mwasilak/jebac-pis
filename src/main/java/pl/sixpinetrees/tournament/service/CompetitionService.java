package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;

import java.util.Collection;

public interface CompetitionService {

    Long createCompetition(CompetitionForm competitionForm);

    Competition getCompetition(Long id);

    Collection<Competition> getCompetitions();

}
