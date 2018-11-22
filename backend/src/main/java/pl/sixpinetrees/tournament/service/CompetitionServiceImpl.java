package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository competitionRepository;

    private PlayerRepository playerRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, PlayerRepository playerRepository) {
        this.competitionRepository = competitionRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public Long createCompetition(CompetitionForm competitionForm) {

        List<Player> players = playerRepository.findByIdIn(competitionForm.getPlayerIds());

        Collections.shuffle(players);

        Competition competition = competitionRepository.save(new Competition(competitionForm.getName(),
                players,
                competitionForm.getNumberOfWinsRequired(),
                competitionForm.getNumberOfPointsToWin()));
        return competition.getId();
    }
}
