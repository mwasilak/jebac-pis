package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.dto.GameRow;
import pl.sixpinetrees.tournament.domain.dto.MatchForm;
import pl.sixpinetrees.tournament.service.MatchService;

import javax.validation.Valid;

@Controller
@Profile("never")
@RequestMapping("/api/matches")
public class MatchController {

    MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @ModelAttribute("module")
    public String module() {
        return "match";
    }

    @GetMapping
    public String matches(Model model) {

        model.addAttribute("matches", matchService.getMatches());
        return "matches";
    }

    @GetMapping("/{matchId}")
    public String match(@PathVariable("matchId") Long matchId, Model model) {

        model.addAttribute("match", matchService.getMatch(matchId)
                .orElseThrow(NotFoundException::new));
        return "match";
    }

    @GetMapping("/{matchId}/edit")
    public String matchForm(@PathVariable("matchId") Long matchId, MatchForm matchForm, Model model) {

        model.addAttribute("match", matchService.getMatch(matchId)
                .orElseThrow(NotFoundException::new));
        return "matchForm";
    }

    @PostMapping(value = "/{matchId}/edit", params = { "addGame" })
    public String addRow(@PathVariable("matchId") Long matchId, final MatchForm matchForm, Model model) {
        model.addAttribute("match", matchService.getMatch(matchId)
                .orElseThrow(NotFoundException::new));
        GameRow gameRow = new GameRow();
        matchForm.getGameRows().add(gameRow);
        return "matchForm";
    }


    @PostMapping("/{matchId}/edit")
    public String processMatchForm(@PathVariable("matchId") Long matchId, @Valid MatchForm matchForm, BindingResult bindingResult) {

        System.out.println("editMatch");

        if(bindingResult.hasErrors()) {
            return "matchForm";
        }

        matchForm.setId(matchId);
        Long id = matchService.updateMatch(matchForm);
        return "redirect:/matches/" + id;
    }

}
