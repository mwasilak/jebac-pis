package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;


import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Competition getCompetition(Long id) {
        return competitionRepository.findOne(id);
    }

    @Override
    public Collection<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    @Transactional
    public Long createCompetition(CompetitionForm competitionForm) {

        Competition competition = competitionRepository.save(new Competition(competitionForm));
        return competition.getId();
    }
}
