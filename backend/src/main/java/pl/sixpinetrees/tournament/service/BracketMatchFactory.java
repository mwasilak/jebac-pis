package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Player;

import java.util.List;
import java.util.Map;

public interface BracketMatchFactory {

    public Map<BracketPosition, Match> generateMatches(Competition competition, List<Player> players);
}
