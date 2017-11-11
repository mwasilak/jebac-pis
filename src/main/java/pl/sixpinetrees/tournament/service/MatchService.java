package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;

public interface MatchService {

    Match getMatch(Long id);

    Collection<Match> getMatches();

    Collection<Match> getMatchByPlayer(Long id);

}
