package pl.sixpinetrees.tournament.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by maciej on 20.03.17.
 */
@Entity
public class Competition {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private Collection<Player> players;

    @OneToMany
    private Collection<Stage> stages;
}
