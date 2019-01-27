package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.util.Calculator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Competition {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "bracketPosition")
    @JoinColumn(
            name = "competitionId"
    )
    private Map<BracketPosition, Match> matches;

    private Integer numberOfPlayers;

    private Integer numberOfRounds;

    private Integer numberOfMatchesInFirstRound;

    @Embedded
    private VictoryConditions victoryConditions;

    private Long winningPlayerId;

    public Competition() {
    }

    public Competition(String name, List<Player> players, Integer numberOfWinsRequired, Integer numberOfPointsToWin) {

        matches = new HashMap<>();
        this.name = name;
        this.victoryConditions = new VictoryConditions(numberOfWinsRequired, numberOfPointsToWin);
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

    public Integer calculateMatchesInRound(Integer round) {
        if (round == 1) {
            return numberOfMatchesInFirstRound;
        }
        return Calculator.pow2N(numberOfRounds - round);
    }

    public Integer calculateMaxMatchesInRound(Integer round) {
        return Calculator.pow2N(numberOfRounds - round);
    }

    private void generateRoundMatches(Integer round, Integer matchesInRound) {

        for (Integer match = 1; match <= matchesInRound; match++) {
            String name = "1/" + Calculator.pow2N(numberOfRounds - round).toString() + "-final no. " + match.toString();
            matches.put(new BracketPosition(round, match), new Match(name, round, match));
        }
    }

    private void assignPlayers(List<Player> players) {
        for(Integer playerNumber = 0; playerNumber<players.size(); playerNumber++) {
            assignPlayerToMatchSlot(playerNumber, players.get(playerNumber));
        }
    }

    private void assignPlayerToMatchSlot(Integer playerNumber, Player player) {
        Integer bracketRound, bracketPosition, playerSlot;

        if(playerNumber < 2*numberOfMatchesInFirstRound) {
            bracketRound = 1;
            bracketPosition = (playerNumber / 2) + 1;
            playerSlot = playerNumber % 2 + 1;
        } else {
            bracketRound = 2;
            bracketPosition = ((playerNumber - numberOfMatchesInFirstRound) / 2)+1;
            playerSlot = (playerNumber-numberOfMatchesInFirstRound) % 2 + 1;
        }
        Match match = matches.get(new BracketPosition(bracketRound, bracketPosition));
        match.assignPlayerToSlot(player, playerSlot);
    }

    public void assignWinner(Long playerId) {
        this.winningPlayerId = playerId;
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

    public Match getMatchByPosition(Integer round, Integer position) {
        return matches.get(new BracketPosition(round, position));
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

    public Long getWinningPlayerId() {
        return winningPlayerId;
    }

    public VictoryConditions getVictoryConditions() {
        return victoryConditions;
    }
}
