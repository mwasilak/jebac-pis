package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.RegistrationForm;
import pl.sixpinetrees.tournament.service.MatchService;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
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
    public Collection<Player> getPlayers() {

        return playerService.getPlayers();
    }

    @GetMapping("/{playerId}")
    public Player player(@PathVariable("playerId") long playerId) {

        return playerService.getPlayer(playerId).orElseThrow(NotFoundException::new);
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
