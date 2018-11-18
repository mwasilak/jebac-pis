package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.service.MatchService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    MatchRepository matchRepository;

    MatchService matchService;

    @Autowired
    public MatchController(MatchRepository matchRepository, MatchService matchService) {
        this.matchRepository = matchRepository;
        this.matchService = matchService;
    }

    @GetMapping
    public Collection<Match> matches() {

        return matchRepository.findAll();
    }

    @GetMapping("/{matchId}")
    public Match match(@PathVariable("matchId") Long matchId) {

        return matchRepository.findById(matchId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/edit/{matchId}")
    public ResponseEntity<?> processMatchForm(@PathVariable("matchId") Long matchId, @Valid @RequestBody MatchForm matchForm) {

        matchForm.setId(matchId);
        Long id = matchService.updateMatch(matchForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.OK);
    }

}
