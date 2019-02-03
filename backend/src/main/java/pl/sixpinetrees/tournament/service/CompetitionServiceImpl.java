package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository competitionRepository;

    private PlayerRepository playerRepository;

    private MatchRepository matchRepository;

    private BracketMatchFactory bracketMatchFactory;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, PlayerRepository playerRepository, MatchRepository matchRepository,
                                  BracketMatchFactory bracketMatchFactory) {
        this.competitionRepository = competitionRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.bracketMatchFactory = bracketMatchFactory;
    }

    @Override
    @Transactional
    public Long createCompetition(CompetitionForm competitionForm) {

        List<Player> players = playerRepository.findByIdIn(competitionForm.getPlayerIds());

        Collections.shuffle(players);

        Competition competition = competitionRepository.save(new Competition(competitionForm.getName(),
                players.size(),
                competitionForm.getNumberOfWinsRequired(),
                competitionForm.getNumberOfPointsToWin()));

        Map<BracketPosition, Match> matches = bracketMatchFactory.generateMatches(competition, players);

        matchRepository.saveAll(matches.values());

        return competition.getId();
    }
}
