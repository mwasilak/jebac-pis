package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.List;
import java.util.Map;

public interface BracketMatchFactory {

    Map<BracketPosition, Match> generateMatches(Competition competition, List<Long> playerIds);
}
