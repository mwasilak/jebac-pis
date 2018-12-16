package pl.sixpinetrees.tournament.domain.projections;

public interface CompetitionGameSettings {

    Integer getNumberOfWinsRequired();

    void setNumberOfWinsRequired(Integer numberOfWinsRequired);

    Integer getNumberOfPointsToWin();

    void setNumberOfPointsToWin(Integer numberOfPointsToWin);
}
