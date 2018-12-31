package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.domain.projections.CompetitionGameSettings;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.web.NotFoundException;

@Service
@Transactional(readOnly = true)
public class ResultRegistrationServiceImpl implements ResultRegistrationService {

    private MatchRepository matchRepository;

    private CompetitionRepository competitionRepository;

    private ResultRegistrationFormValidator resultRegistrationFormValidator;

    @Autowired
    public ResultRegistrationServiceImpl(MatchRepository matchRepository, CompetitionRepository competitionRepository, ResultRegistrationFormValidator resultRegistrationFormValidator) {
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
        this.resultRegistrationFormValidator = resultRegistrationFormValidator;
    }

    @Override
    @Transactional
    public Long registerResults(Long matchId, ResultRegistrationForm resultRegistrationForm) {

        Match match = matchRepository.findById(matchId).orElseThrow( () -> new NotFoundException("Match with id " + matchId + " not found") );

        validateResultRegistrationForm(resultRegistrationForm, match.getCompetitionId());
        match.registerResults(resultRegistrationForm);



        return match.getId();
    }

    private void validateResultRegistrationForm(ResultRegistrationForm resultRegistrationForm, Long competitionId) {
        CompetitionGameSettings gameSettings = competitionRepository.findById(competitionId, CompetitionGameSettings.class)
                .orElseThrow( () -> new NotFoundException("Competition with id " + competitionId + " not found.") );

        resultRegistrationFormValidator.isValid(resultRegistrationForm, gameSettings);
    }

}
