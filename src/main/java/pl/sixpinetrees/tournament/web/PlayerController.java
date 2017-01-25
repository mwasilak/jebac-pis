package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;

/**
 * Project: tournament
 * Created by maciej on 21.01.17.
 */
@Controller
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String players(Model model) {

        model.addAttribute("players", playerService.getPlayers());
        return "players";
    }

    @RequestMapping(value="/{playerId}", method=RequestMethod.GET)
    public String player(@PathVariable("playerId") long playerId, Model model) {

        model.addAttribute("player", playerService.getPlayer(playerId));
        return "player";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("player", new Player());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid Player player, Errors errors) {

        if (errors.hasErrors()) {
            return "registerForm";
        }

        Long id = playerService.registerPlayer(player);
        return "redirect:/players/" + id;
    }
}
