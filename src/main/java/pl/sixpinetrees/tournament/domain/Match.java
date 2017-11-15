package pl.sixpinetrees.tournament.domain;

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
    private Collection<Round> rounds;

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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Collection<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public Collection<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Collection<Round> rounds) {
        this.rounds = rounds;
    }

    public BracketPosition getPosition() {
        return position;
    }
}
