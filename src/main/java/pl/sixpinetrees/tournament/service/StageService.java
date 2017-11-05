package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.domain.dto.StageForm;

import java.util.Collection;

public interface StageService {

    Long createStage(StageForm stageForm);

    Stage getStage(Long id);

    Collection<Stage> getStages();

}
