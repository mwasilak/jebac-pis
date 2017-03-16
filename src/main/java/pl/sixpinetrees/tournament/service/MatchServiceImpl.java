package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.repository.MatchRepository;

import java.util.Collection;

/**
 * Created by maciej on 19.02.17.
 */
@Service
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match getMatch(Long id) {
        return matchRepository.findOne(id);
    }

    @Override
    public Collection<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Collection<Match> getMatchByPlayer(Long id) {
        return matchRepository.findByPlayer1IdOrPlayer2Id(id);
    }

}
