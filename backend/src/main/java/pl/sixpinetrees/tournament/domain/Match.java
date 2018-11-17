package pl.sixpinetrees.tournament.domain;

import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static pl.sixpinetrees.tournament.util.Calculator.pow2N;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Game> games;

    @Embedded
    private BracketPosition position;

    public Match() {
    }

    public Match(String name, Integer round, Integer position) {
        this.name = name;
        this.position = new BracketPosition(round, position);
    }

    public String calculateBracketKey() {
        return Integer.toString(getPosition().getRound())
                + "/"
                + Integer.toString(pow2N(getPosition().getRound())*getPosition().getPosition() - pow2N(getPosition().getRound()-1));
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

    public Collection<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public Collection<Game> getGames() {
        return games;
    }

    public BracketPosition getPosition() {
        return position;
    }

    public void update(MatchForm matchForm) {

        this.games.clear();
        Integer i = 1;
        for(GameRow game : matchForm.getGames()) {
            Game newGame = new Game(i, game.getScorePlayer1(), game.getScorePlayer2());
            this.games.add(newGame);
            i++;
        }
    }
}
