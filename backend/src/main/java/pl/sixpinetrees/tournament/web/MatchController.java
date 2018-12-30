package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.service.ResultRegistrationService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    MatchRepository matchRepository;

    ResultRegistrationService resultRegistrationService;

    @Autowired
    public MatchController(MatchRepository matchRepository, ResultRegistrationService resultRegistrationService) {
        this.matchRepository = matchRepository;
        this.resultRegistrationService = resultRegistrationService;
    }

    @GetMapping
    public Collection<Match> matches() {

        return matchRepository.findAll();
    }

    @GetMapping("/{matchId}")
    public Match match(@PathVariable("matchId") Long matchId) {

        return matchRepository.findById(matchId).orElseThrow( () -> new NotFoundException("Match with id " + matchId + " not found") );
    }

    @PostMapping("/edit/{matchId}")
    public ResponseEntity<?> processMatchForm(@PathVariable("matchId") Long matchId, @Valid @RequestBody ResultRegistrationForm resultRegistrationForm) {

        resultRegistrationForm.setId(matchId);
        Long id = resultRegistrationService.registerResults(resultRegistrationForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.OK);
    }

}
