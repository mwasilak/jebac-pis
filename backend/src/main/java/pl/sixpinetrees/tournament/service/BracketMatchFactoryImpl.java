package pl.sixpinetrees.tournament.service;

import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.util.Calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BracketMatchFactoryImpl implements BracketMatchFactory {

    @Override
    public Map<BracketPosition, Match> generateMatches(Competition competition, List<Player> players) {
        Map<BracketPosition, Match> matches = new HashMap<>();

        for (Integer round = 1; round <= competition.getNumberOfRounds(); round++) {
            generateRoundMatches(matches, round, competition);
        }
        assignPlayersToMatches(competition, players, matches);
        return matches;
    }

    private void generateRoundMatches(Map<BracketPosition, Match> matches, Integer round, Competition competition) {

        Integer matchesInRound = competition.calculateMatchesInRound(round);

        for (Integer match = 1; match <= matchesInRound; match++) {
            String name = "1/" + Calculator.pow2N(competition.getNumberOfRounds() - round).toString() + "-final no. " + match.toString();
            matches.put(new BracketPosition(round, match), new Match(name, competition.getId(), round, match));
        }
    }

    void assignPlayersToMatches(Competition competition, List<Player> players, Map<BracketPosition, Match> matches ) {
        for(Integer playerNumber = 0; playerNumber<players.size(); playerNumber++) {

            BracketPosition startingPosition = calculateStartingBracketPosition(competition, playerNumber);
            Integer startingSlot = calculateStartingPlayerSlot(competition, playerNumber);
            matches.get(startingPosition).assignPlayerToSlot(players.get(playerNumber), startingSlot);
        }
    }

    private BracketPosition calculateStartingBracketPosition(Competition competition, Integer playerNumber) {

        if(playerNumber < 2*competition.getNumberOfMatchesInFirstRound()) {
            return new BracketPosition(1, (playerNumber / 2) + 1);
        } else {
            return new BracketPosition(2, ((playerNumber - competition.getNumberOfMatchesInFirstRound()) / 2)+1);
        }
    }

    private Integer calculateStartingPlayerSlot(Competition competition, Integer playerNumber) {
        if(playerNumber < 2*competition.getNumberOfMatchesInFirstRound()) {
            return playerNumber % 2 + 1;
        } else {
            return (playerNumber - competition.getNumberOfMatchesInFirstRound()) % 2 + 1;
        }
    }

}
