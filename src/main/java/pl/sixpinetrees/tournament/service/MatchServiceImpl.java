package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.web.NotFoundException;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public Collection<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Collection<Match> getMatchByPlayer(Long id) {
        return matchRepository.findByPlayer1IdOrPlayer2Id(id, id);
    }

    @Override
    @Transactional
    public Long updateMatch(MatchForm matchForm) {
        Match match = matchRepository.findById(matchForm.getId()).orElseThrow(NotFoundException::new);

        match.update(matchForm);

        return match.getId();
    }

}
