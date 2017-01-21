package pl.sixpinetrees.tournament.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maciej on 14.01.17.
 */
@Entity
public class MatchPlayer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Match match;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("roundNumber ASC")
    private List<MatchPlayerRound> matchPlayerRounds;

    public MatchPlayer() {
    }

    public MatchPlayer(Match match, Player player) {
        this.match = match;
        this.player = player;
    }

    public Long getId() {

        return id;
    }

    public Match getMatch() {
        return match;
    }

    public Player getPlayer() {
        return player;
    }

    public List<MatchPlayerRound> getMatchPlayerRounds() {
        return matchPlayerRounds;
    }

    public void setMatchPlayerRounds(List<MatchPlayerRound> matchPlayerRounds) {
        this.matchPlayerRounds = matchPlayerRounds;
    }
}
