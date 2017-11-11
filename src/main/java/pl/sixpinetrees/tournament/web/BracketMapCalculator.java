package pl.sixpinetrees.tournament.web;

import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.util.Calculator;

import java.util.Map;
import java.util.stream.Collectors;

public class BracketMapCalculator {
    public BracketMapCalculator() {
    }

    public Map<String, Match> prepareMatchBracketMap(Stage stage) {
        return stage.getMatches()
                .stream()
                .collect(
                        Collectors.toMap(Match::calculateBracketKey, match -> match)
                );
    }

}