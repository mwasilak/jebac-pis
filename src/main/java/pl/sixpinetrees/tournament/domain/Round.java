package pl.sixpinetrees.tournament.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by maciej on 15.01.17.
 */
@Entity
public class Round {

    @Id @GeneratedValue
    private Long id;

    private Integer roundNumber;

    private Integer scorePlayer1;

    private Integer scorePlayer2;

    public Round() {
    }

    public Round(Integer roundNumber, Integer scorePlayer1, Integer scorePlayer2) {
        this.roundNumber = roundNumber;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public Integer getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(Integer scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public Integer getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(Integer scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }
}
