package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;

/**
 * Created by maciej on 19.02.17.
 */
public interface MatchService {

    Match getMatch(Long id);

    Collection<Match> getMatches();

    Collection<Match> getMatchByPlayer(Long id);

}
