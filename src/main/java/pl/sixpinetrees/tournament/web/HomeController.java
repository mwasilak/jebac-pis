package pl.sixpinetrees.tournament.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("module")
    public String module() {
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
