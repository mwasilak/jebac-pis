package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.service.ServiceValidationException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(updatable = false)
    private Long competitionId;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Game> games;

    @Embedded
    private BracketPosition bracketPosition;

    private LocalDateTime resultRegistrationTime;

    private Winner winner;

    public Match() {
        this.winner = Winner.UNKNOWN;
    }

    public Match(String name, Long competitionId, Integer round, Integer position) {
        this.name = name;
        this.competitionId = competitionId;
        this.bracketPosition = new BracketPosition(round, position);
        this.winner = Winner.UNKNOWN;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void assignPlayerToSlot(Player player, Integer slot) {
        if(slot == 1) {
            this.player1 = player;
        } else if (slot == 2) {
            this.player2 = player;
        }
    }

    public Collection<Game> getGames() {
        return games;
    }

    public BracketPosition getBracketPosition() {
        return bracketPosition;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public LocalDateTime getResultRegistrationTime() {
        return resultRegistrationTime;
    }

    public Winner getWinner() {
        return winner;
    }

    public Optional<Player> getWinningPlayer() {
        if(winner == Winner.PLAYER1) {
            return Optional.ofNullable(player1);
        } else if (winner == Winner.PLAYER2) {
            return Optional.ofNullable(player2);
        } else {
            return Optional.empty();
        }
    }

    public void registerResults(ResultRegistrationForm resultRegistrationForm, Winner winner) {

        validateMatchPlayers();
        validateMatchRegistrationStatus();

        games.clear();
        Integer i = 1;
        for(GameRow game : resultRegistrationForm.getGames()) {
            Game newGame = new Game(i, game.getScorePlayer1(), game.getScorePlayer2());
            games.add(newGame);
            i++;
        }
        resultRegistrationTime = LocalDateTime.now();
        this.winner = winner;
    }

    private void validateMatchPlayers() {
        if(player1 == null || player2 == null) {
            List<String> errorList = new ArrayList<>();
            errorList.add("Cannot register results when both players are not assigned to the match.");
            throw new ServiceValidationException(errorList);
        }
    }

    private void validateMatchRegistrationStatus() {
        if(resultRegistrationTime != null) {
            List<String> errorList = new ArrayList<>();
            errorList.add("Cannot register results when results already registered.");
            throw new ServiceValidationException(errorList);
        }
    }
}
