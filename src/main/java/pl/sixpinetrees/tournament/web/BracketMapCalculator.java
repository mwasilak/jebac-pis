package pl.sixpinetrees.tournament.web;

import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.Map;
import java.util.stream.Collectors;

public class BracketMapCalculator {
    public BracketMapCalculator() {
    }

    public Map<String, Match> prepareMatchBracketMap(Competition competition) {
        return competition.getMatches()
                .stream()
                .collect(
                        Collectors.toMap(Match::calculateBracketKey, match -> match)
                );
    }

}