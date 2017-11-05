package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sixpinetrees.tournament.domain.dto.StageForm;
import pl.sixpinetrees.tournament.repository.StageRepository;
import pl.sixpinetrees.tournament.service.StageService;

import javax.validation.Valid;

/**
 * Created by maciej on 23.03.17.
 */
@Controller
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping("/{stageId}")
    public String stage(@PathVariable("stageId") Long stageId, Model model) {

        model.addAttribute("stage", stageService.getStage(stageId));
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
