package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;

import java.util.Collection;

public interface MatchService {

    Collection<Match> getMatchByPlayer(Long id);

    Long updateMatch(MatchForm matchForm);

}
