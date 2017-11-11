package pl.sixpinetrees.tournament.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: tournament
 * Created by maciej on 25.01.17.
 */
@Controller
public class HomeController {

    @ModelAttribute("module")
    public String module() {
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
