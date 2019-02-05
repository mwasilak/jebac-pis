package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.*;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
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

        Match match = matchRepository.findById(matchId)
                .orElseThrow( () -> new NotFoundException("Match with id " + matchId + " not found") );

        Competition competition = competitionRepository.findById(match.getCompetitionId())
                .orElseThrow(InternalError::new);

        Winner winner = resultRegistrationFormValidator.isValid(resultRegistrationForm, competition.getVictoryConditions());
        match.registerResults(resultRegistrationForm, winner);

        advanceWinnerToNextRound(match, competition);

        return match.getId();
    }

    private void advanceWinnerToNextRound(Match match, Competition competition) {
        if(match.getBracketPosition().getRound() == competition.getNumberOfRounds()) {
            assignWinnerToCompetition(match, competition);
        } else {
            assignWinnerToNextMatch(match);
        }
    }

    private void assignWinnerToCompetition(Match match, Competition competition) {
        Long winningPlayerId = match.getWinningPlayerId()
                .orElseThrow(InternalError::new);
        competition.assignWinner(winningPlayerId);
    }

    private void assignWinnerToNextMatch(Match match) {
        BracketPosition nextMatchBracketPosition = new BracketPosition(match.getBracketPosition().getRound()+1, (match.getBracketPosition().getPosition()+1)/2);

        Match nextMatch = matchRepository.findByCompetitionIdAndBracketPosition(match.getCompetitionId(), nextMatchBracketPosition)
                .orElseThrow(InternalError::new);

        Long winningPlayerId = match.getWinningPlayerId()
                .orElseThrow(InternalError::new);

        nextMatch.assignPlayerToSlot(winningPlayerId, ((match.getBracketPosition().getPosition()+1) % 2)+1);

    }


}
