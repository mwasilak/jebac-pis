package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.RegistrationForm;
import pl.sixpinetrees.tournament.service.MatchService;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    public PlayerController() {
    }

    @ModelAttribute("module")
    public String module() {
        return "player";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String players(Model model) {

        model.addAttribute("players", playerService.getPlayers());
        return "players";
    }

    @RequestMapping(value="/{playerId}", method=RequestMethod.GET)
    public String player(@PathVariable("playerId") long playerId, Model model) {

        model.addAttribute("player", playerService.getPlayer(playerId)
                        .orElseThrow(NotFoundException::new));
        model.addAttribute("matches", matchService.getMatchByPlayer(playerId));
        return "player";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(RegistrationForm registrationForm, Model model) {
        return "registrationForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid RegistrationForm registrationForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        Long id = playerService.registerPlayer(registrationForm);
        return "redirect:/players/" + id;
    }
}
