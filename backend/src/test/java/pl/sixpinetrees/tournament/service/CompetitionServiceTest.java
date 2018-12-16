package pl.sixpinetrees.tournament.service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;

/**
 * Created by maciej on 28.03.17.
 */
@SpringBootTest
public class CompetitionServiceTest {

    @MockBean
    private CompetitionRepository competitionRepository;

    @Test
    public void createCompetition() throws Exception {

    }

}