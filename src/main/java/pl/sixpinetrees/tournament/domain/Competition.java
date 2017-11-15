package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.util.Calculator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Competition {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name = "position")
    private Map<BracketPosition, Match> matches;

    private Integer numberOfPlayers;

    private Integer numberOfRounds;

    private Integer numberOfMatchesInFirstRound;

    public Competition() {
    }

    public Competition(String name, List<Player> players) {

        matches = new HashMap<>();
        this.name = name;
        calculateRounds(players.size());
        generateRounds();
        assignPlayers(players);
    }

    private void calculateRounds(Integer noOfPlayers) {
        numberOfPlayers = noOfPlayers;
        numberOfRounds = Calculator.log2ceil(numberOfPlayers);
        numberOfMatchesInFirstRound = numberOfPlayers - Calculator.pow2N(numberOfRounds - 1);
    }

    private void generateRounds() {

        for (Integer round = 1; round <= numberOfRounds; round++) {
            Integer matchesInRound = calculateMatchesInRound(round);
            generateRoundMatches(round, matchesInRound);
        }
    }

    private Integer calculateMatchesInRound(Integer round) {
        if (round == 1) {
            return numberOfMatchesInFirstRound;
        }
        return Calculator.pow2N(numberOfRounds - round);
    }

    private void generateRoundMatches(Integer round, Integer matchesInRound) {

        for (Integer match = 1; match <= matchesInRound; match++) {
            String name = "1/" + Calculator.pow2N(numberOfRounds - round).toString() + "-final no. " + match.toString();
            matches.put(new BracketPosition(round, match), new Match(name, round, match));
        }
    }

    private void assignPlayers(List<Player> players) {
        for(Integer i = 0; i<players.size(); i++) {
            if(i < 2*numberOfMatchesInFirstRound) {
                if((i % 2) == 0 ) {
                    matches.get(new BracketPosition(1, (i / 2)+1)).setPlayer1(players.get(i));
                } else {
                    matches.get(new BracketPosition(1, (i / 2)+1)).setPlayer2(players.get(i));
                }
            } else {
                if(((i-numberOfMatchesInFirstRound) % 2) == 0 ) {
                    matches.get(new BracketPosition(2, ((i-numberOfMatchesInFirstRound) / 2)+1)).setPlayer1(players.get(i));
                } else {
                    matches.get(new BracketPosition(2, ((i-numberOfMatchesInFirstRound) / 2)+1)).setPlayer2(players.get(i));
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<BracketPosition, Match> getMatches() {
        return matches;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }

    public Integer getNumberOfMatchesInFirstRound() {
        return numberOfMatchesInFirstRound;
    }


}
