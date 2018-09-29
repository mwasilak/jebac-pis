package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.dto.RegistrationForm;
import pl.sixpinetrees.tournament.service.MatchService;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;

@Controller
@Profile("never")
@RequestMapping("/api/players")
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

    @GetMapping
    public String players(Model model) {

        model.addAttribute("players", playerService.getPlayers());
        return "players";
    }

    @GetMapping("/{playerId}")
    public String player(@PathVariable("playerId") long playerId, Model model) {

        model.addAttribute("player", playerService.getPlayer(playerId)
                        .orElseThrow(NotFoundException::new));
        model.addAttribute("matches", matchService.getMatchByPlayer(playerId));
        return "player";
    }

    @GetMapping("/register")
    public String registerForm(RegistrationForm registrationForm, Model model) {
        return "registrationForm";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid RegistrationForm registrationForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        Long id = playerService.registerPlayer(registrationForm);
        return "redirect:/players/" + id;
    }
}
