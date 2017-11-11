package pl.sixpinetrees.tournament.domain.dto;

public class StageForm {

    private String name;

    private Integer numberOfPlayers;

    public StageForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
