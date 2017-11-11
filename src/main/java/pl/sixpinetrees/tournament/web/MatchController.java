package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET)
    public String matches(Model model) {

        model.addAttribute("matches", matchService.getMatches());
        return "matches";
    }

    @RequestMapping(value = "/{matchId}", method = RequestMethod.GET)
    public String match(@PathVariable("matchId") Long matchId, Model model) {

        model.addAttribute("match", matchService.getMatch(matchId));
        return "match";
    }
}
