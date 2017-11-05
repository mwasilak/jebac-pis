package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.domain.dto.StageForm;
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

    public Stage(StageForm stageForm) {

        calculateRounds(stageForm.getNumberOfPlayers());
    }

    private void calculateRounds(Integer noOfPlayers) {
        numberOfPlayers = noOfPlayers;
        numberOfRounds = Calculator.log2ceil(numberOfPlayers);
        numberOfMatchesInFirstRound = numberOfPlayers - Calculator.pow2N(numberOfRounds - 1);

        matches = new ArrayList<>();

        generateRounds();
    }

    private void generateRounds() {

        for (Integer round = 1; round <= numberOfRounds; round++) {
            Integer matchesInRound = calculateMatchesInRound(round);
            generateRoundMatches(round, matchesInRound);
        }
    }

    private Integer calculateMatchesInRound(Integer round) {
        Integer matchesInRound;
        if (round == 1) {
            return numberOfMatchesInFirstRound;
        }
        return Calculator.pow2N(numberOfRounds - round);
    }

    private void generateRoundMatches(Integer round, Integer matchesInRound) {

        for (Integer match = 1; match <= matchesInRound; match++) {
            String name = "1/" + Calculator.pow2N(round).toString() + "-final no. " + match.toString();
            matches.add(new BracketMatch(name, round, match));
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

    public Collection<BracketMatch> getMatches() {
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
