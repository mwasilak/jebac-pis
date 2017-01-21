package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sixpinetrees.tournament.service.PlayerService;

/**
 * Created by maciej on 21.01.17.
 */
@Controller
@RequestMapping("/players")
public class PlayerController {

    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String players(Model model) {
        model.addAttribute("players", playerService.getPlayers());
        return "players";
    }



}
