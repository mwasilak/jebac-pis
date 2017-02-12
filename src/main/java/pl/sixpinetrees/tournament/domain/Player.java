package pl.sixpinetrees.tournament.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Project: tournament
 * Created by maciej on 25.12.16.
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @OneToMany
    private Collection<MatchPlayer> matchPlayers;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
