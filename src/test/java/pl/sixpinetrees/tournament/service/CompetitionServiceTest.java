package pl.sixpinetrees.tournament.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;

/**
 * Created by maciej on 28.03.17.
 */
@RunWith(SpringRunner.class)
public class CompetitionServiceTest {

    @MockBean
    private CompetitionRepository competitionRepository;

    @Test
    public void createCompetition() throws Exception {

    }

}