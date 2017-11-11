package pl.sixpinetrees.tournament.domain.dto;

public class CompetitionForm {

    private String name;

    private Integer numberOfPlayers;

    public CompetitionForm() {
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
