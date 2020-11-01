package pl.sixpinetrees.tournament.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class ResultRegistrationForm {

    @NotEmpty
    @Valid
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
