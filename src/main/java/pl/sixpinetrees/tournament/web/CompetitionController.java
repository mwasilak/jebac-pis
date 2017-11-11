package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.service.CompetitionService;

import javax.validation.Valid;

import static pl.sixpinetrees.tournament.util.Calculator.pow2N;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    private final BracketMapCalculator bracketMapCalculator = new BracketMapCalculator();

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/{competitionId}")
    public String competition(@PathVariable("competitionId") Long competitionId, Model model) {
        Competition competition = competitionService.getCompetition(competitionId);
        model.addAttribute("competition", competition);
        model.addAttribute("matchBracket", bracketMapCalculator.prepareMatchBracketMap(competition));
        model.addAttribute("bracketMaxSize", pow2N(competition.getNumberOfRounds()+1)-1);
        return "competition";
    }

    @GetMapping("/add")
    public String competitionForm(CompetitionForm competitionForm) {
        return "competitionForm";
    }

    @PostMapping("/add")
    public String processCompetitionForm(@Valid CompetitionForm competitionForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "competitionForm";
        }

        Long id = competitionService.createCompetition(competitionForm);
        return "redirect:/competitions/" + id;
    }

}
