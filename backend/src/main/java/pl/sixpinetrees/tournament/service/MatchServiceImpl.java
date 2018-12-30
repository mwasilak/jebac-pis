package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.web.NotFoundException;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;

    private CompetitionRepository competitionRepository;

    private MatchFormValidator matchFormValidator;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, CompetitionRepository competitionRepository, MatchFormValidator matchFormValidator) {
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
        this.matchFormValidator = matchFormValidator;
    }

    @Override
    public Collection<Match> getMatchByPlayer(Long id) {
        return matchRepository.findByPlayer1IdOrPlayer2Id(id, id);
    }

    @Override
    @Transactional
    public Long updateMatch(MatchForm matchForm) {
        Match match = matchRepository.findById(matchForm.getId()).orElseThrow( () -> new NotFoundException("Match with id " + matchForm.getId() + " not found") );

        CompetitionGameSettings gameSettings = competitionRepository.findById(match.getCompetitionId(), CompetitionGameSettings.class)
                .orElseThrow( () -> new NotFoundException("Competition with id " + match.getCompetitionId() + " not found.") );

        matchFormValidator.isValid(matchForm, gameSettings);

        match.registerResults(matchForm);

        return match.getId();
    }

}
