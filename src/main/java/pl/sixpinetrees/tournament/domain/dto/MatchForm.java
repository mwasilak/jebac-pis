package pl.sixpinetrees.tournament.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class MatchForm {

    private Long id;

    private List<GameRow> gameRows;

    public MatchForm() {
        gameRows = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GameRow> getGameRows() {
        return gameRows;
    }

    public void setGameRows(List<GameRow> gameRows) {
        this.gameRows = gameRows;
    }
}
