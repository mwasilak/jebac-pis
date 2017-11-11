package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Stage;
import pl.sixpinetrees.tournament.domain.dto.StageForm;
import pl.sixpinetrees.tournament.service.StageService;

import javax.validation.Valid;
import java.util.Map;

import static pl.sixpinetrees.tournament.util.Calculator.pow2N;

@Controller
@RequestMapping("/stages")
public class StageController {

    private final BracketMapCalculator bracketMapCalculator = new BracketMapCalculator();

    @Autowired
    private StageService stageService;

    @GetMapping("/{stageId}")
    public String stage(@PathVariable("stageId") Long stageId, Model model) {
        Stage stage = stageService.getStage(stageId);
        model.addAttribute("stage", stage);
        model.addAttribute("matchBracket", bracketMapCalculator.prepareMatchBracketMap(stage));
        model.addAttribute("bracketMaxSize", pow2N(stage.getNumberOfRounds()+1)-1);
        return "stage";
    }

    @GetMapping("/add")
    public String stageForm(StageForm stageForm) {
        return "stageForm";
    }

    @PostMapping("/add")
    public String processAddStage(@Valid StageForm stageForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "stageForm";
        }

        Long id = stageService.createStage(stageForm);
        return "redirect:/stages/" + id;
    }

}
