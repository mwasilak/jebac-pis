package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;
import java.util.Optional;

public interface MatchService {

    Optional<Match> getMatch(Long id);

    Collection<Match> getMatches();

    Collection<Match> getMatchByPlayer(Long id);

}
