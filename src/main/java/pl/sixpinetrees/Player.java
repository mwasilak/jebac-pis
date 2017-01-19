package pl.sixpinetrees;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by maciej on 25.12.16.
 */
@Entity
class Player {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private Collection<MatchPlayer> matchPlayers;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<MatchPlayer> getMatchPlayers() {
        return matchPlayers;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
