package pl.sixpinetrees.tournament.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultRegistrationForm {

    private List<GameRow> games;

    public ResultRegistrationForm() {
        games = new ArrayList<>();
    }

    public List<GameRow> getGames() {
        return games;
    }

    public void setGames(List<GameRow> games) {
        this.games = games;
    }
}
