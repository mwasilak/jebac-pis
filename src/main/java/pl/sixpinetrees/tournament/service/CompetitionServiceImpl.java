package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.PlayerRepository;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Optional<Competition> getCompetition(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Collection<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    @Transactional
    public Long createCompetition(CompetitionForm competitionForm) {

        List<Player> players = playerRepository.findByIdIn(competitionForm.getPlayerIds());

        Collections.shuffle(players);

        Competition competition = competitionRepository.save(new Competition(competitionForm.getName(), players));
        return competition.getId();
    }
}
