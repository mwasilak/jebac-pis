package pl.sixpinetrees;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maciej on 25.12.16.
 */
@Entity
class Match {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatchPlayer> matchPlayers;

    public Match() {
    }

    public Match(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MatchPlayer> getMatchPlayers() {
        return matchPlayers;
    }

    public void setMatchPlayers(List<MatchPlayer> matchPlayers) {
        this.matchPlayers = matchPlayers;
    }
}
