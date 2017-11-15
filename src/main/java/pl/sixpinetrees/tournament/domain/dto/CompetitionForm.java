package pl.sixpinetrees.tournament.domain.dto;


import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CompetitionForm {

    @Size(min = 1, max = 32, message = "Competition name length must be between {min} and {max}.")
    private String name;

    @Size(min = 2, message = "Competition must be joined by at least 2 players.")
    private List<Long> playerIds = new ArrayList<>();

    public CompetitionForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Long> playerIds) {
        this.playerIds = playerIds;
    }
}
