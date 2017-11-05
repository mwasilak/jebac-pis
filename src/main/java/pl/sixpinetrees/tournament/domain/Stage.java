package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.util.Calculator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Stage {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<BracketMatch> matches;

    private Integer numberOfPlayers;

    private Integer numberOfRounds;

    private Integer numberOfMatchesInFirstRound;

    public Stage() {
    }

    public Stage(Integer noOfPlayers) {

        numberOfPlayers = noOfPlayers;
        numberOfRounds = Calculator.log2ceil(numberOfPlayers);
        numberOfMatchesInFirstRound = numberOfPlayers - Calculator.pow2N(numberOfRounds - 1);

        ArrayList<BracketMatch> matchesList = new ArrayList<>();

        for (Integer round = 1; round <= numberOfRounds; round++) {

            Integer matchesInRound;
            if (round == 1) {
                matchesInRound = numberOfMatchesInFirstRound;
            } else {
                matchesInRound = Calculator.pow2N(numberOfRounds - round);
            }

            for (Integer match = 1; match <= matchesInRound; match++) {

                String name = "1/" + matchesInRound.toString() + "-final no. " + match.toString();
                matchesList.add(new BracketMatch(name, round, match));
            }

        }
    }
}
