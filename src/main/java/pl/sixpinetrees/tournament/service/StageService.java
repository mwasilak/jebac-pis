package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Stage;

import java.util.Collection;

/**
 * Created by maciej on 23.03.17.
 */
public interface StageService {

    Long createStage(Integer numberOfPlayers);

    Stage getStage(Long id);

    Collection<Stage> getStages();

}
