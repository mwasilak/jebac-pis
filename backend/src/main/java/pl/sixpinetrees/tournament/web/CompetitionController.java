package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.service.CompetitionService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private CompetitionRepository competitionRepository;

    private CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionRepository competitionRepository, CompetitionService competitionService) {
        this.competitionRepository = competitionRepository;
        this.competitionService = competitionService;
    }

    @GetMapping
    public Collection<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    @GetMapping("/{competitionId}")
    public Competition competition(@PathVariable("competitionId") Long competitionId) {
        return competitionRepository.findById(competitionId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/add")
    public ResponseEntity<?> processCompetitionForm(@Valid @RequestBody CompetitionForm competitionForm) {

        Long id = competitionService.createCompetition(competitionForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.CREATED);
    }

}
