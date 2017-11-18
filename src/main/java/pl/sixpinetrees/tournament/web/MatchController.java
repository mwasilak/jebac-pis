package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.service.MatchService;

@Controller
@RequestMapping("/matches")
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
}
