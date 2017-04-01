package pl.sixpinetrees.tournament.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.repository.StageRepository;

import static org.junit.Assert.*;

/**
 * Created by maciej on 28.03.17.
 */
@RunWith(SpringRunner.class)
public class StageServiceTest {

    @MockBean
    private StageRepository stageRepository;

    @Test
    public void createStage() throws Exception {

    }

}