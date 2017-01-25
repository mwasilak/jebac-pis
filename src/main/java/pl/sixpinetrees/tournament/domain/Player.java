package pl.sixpinetrees.tournament.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Project: tournament
 * Created by maciej on 25.12.16.
 */
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private Collection<MatchPlayer> matchPlayers;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<MatchPlayer> getMatchPlayers() {
        return matchPlayers;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
