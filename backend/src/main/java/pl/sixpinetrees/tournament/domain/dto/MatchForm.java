package pl.sixpinetrees.tournament.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class MatchForm {

    private Long id;

    private List<GameRow> games;

    public MatchForm() {
        games = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GameRow> getGames() {
        return games;
    }

    public void setGames(List<GameRow> games) {
        this.games = games;
    }
}
