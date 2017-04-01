package pl.sixpinetrees.tournament.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by maciej on 28.03.17.
 */
@Entity
@DiscriminatorValue("Bracket")
public class BracketMatch extends Match {

    private Integer bracketRound;

    private Integer bracketPosition;

    public BracketMatch() {
    }

    public BracketMatch(String name, Integer round, Integer position) {
        this.name = name;
    }
}
