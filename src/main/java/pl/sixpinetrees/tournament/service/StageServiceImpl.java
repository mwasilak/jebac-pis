package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.repository.StageRepository;

import java.util.Collection;

/**
 * Created by maciej on 23.03.17.
 */
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;

    @Override
    public Stage getStage(Long id) {
        return stageRepository.findOne(id);
    }

    @Override
    public Collection<Stage> getStages() {
        return stageRepository.findAll();
    }

    @Override
    public Long createStage(Integer numberOfPlayers) {

        Stage stage = new Stage();
        return null;
    }
}
