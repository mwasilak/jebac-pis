package pl.sixpinetrees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by maciej on 15.01.17.
 */
@Entity
public class MatchPlayerRound {

    @Id @GeneratedValue
    private Long id;

    private Integer roundNumber;

    private Integer score;

    public MatchPlayerRound() {
    }

    public MatchPlayerRound(Integer roundNumber, Integer score) {
        this.roundNumber = roundNumber;
        this.score = score;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public Integer getScore() {
        return score;
    }
}
