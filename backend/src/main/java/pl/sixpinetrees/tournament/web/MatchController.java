package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.service.MatchService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

//    @ModelAttribute("module")
//    public String module() {
//        return "match";
//    }

    @GetMapping
    public Collection<Match> matches() {

        return matchService.getMatches();
    }

    @GetMapping("/{matchId}")
    public Match match(@PathVariable("matchId") Long matchId) {

        return matchService.getMatch(matchId).orElseThrow(NotFoundException::new);
    }

//    @GetMapping("/{matchId}/edit")
//    public String matchForm(@PathVariable("matchId") Long matchId, MatchForm matchForm, Model model) {
//
//        model.addAttribute("match", matchService.getMatch(matchId)
//                .orElseThrow(NotFoundException::new));
//        return "matchForm";
//    }
//
//    @PostMapping(value = "/{matchId}/edit", params = { "addGame" })
//    public String addRow(@PathVariable("matchId") Long matchId, final MatchForm matchForm, Model model) {
//        model.addAttribute("match", matchService.getMatch(matchId)
//                .orElseThrow(NotFoundException::new));
//        GameRow gameRow = new GameRow();
//        matchForm.getGameRows().add(gameRow);
//        return "matchForm";
//    }


    @PostMapping("/{matchId}/edit")
    public ResponseEntity<?> processMatchForm(@PathVariable("matchId") Long matchId, @Valid @RequestBody MatchForm matchForm) {

        matchForm.setId(matchId);
        Long id = matchService.updateMatch(matchForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.OK);
    }

}
