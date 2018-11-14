package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.service.CompetitionService;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;

import java.util.Collection;

import static pl.sixpinetrees.tournament.util.Calculator.pow2N;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final BracketMapCalculator bracketMapCalculator = new BracketMapCalculator();

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private PlayerService playerService;

    @ModelAttribute("module")
    public String module() {
        return "competition";
    }

    @ModelAttribute("allPlayers")
    public Collection<Player> populatePlayers() {
        return playerService.getPlayers();
    }

    @GetMapping
    public Collection<Competition> getCompetitions() {
        return competitionService.getCompetitions();
    }

    @GetMapping("/{competitionId}")
    public Competition competition(@PathVariable("competitionId") Long competitionId) {
        return competitionService.getCompetition(competitionId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/add")
    public ResponseEntity<?> processCompetitionForm(@Valid @RequestBody CompetitionForm competitionForm) {

        Long id = competitionService.createCompetition(competitionForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.CREATED);
    }

}
