package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.domain.dto.StageForm;
import pl.sixpinetrees.tournament.repository.StageRepository;


import java.util.Collection;

@Service
@Transactional(readOnly = true)
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
    @Transactional
    public Long createStage(StageForm stageForm) {

        Stage stage = stageRepository.save(new Stage(stageForm));
        return stage.getId();
    }
}
