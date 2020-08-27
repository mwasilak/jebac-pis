package pl.sixpinetrees.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.PlayerRepository;
import pl.sixpinetrees.tournament.service.CompetitionService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class TournamentApplication {

    @Configuration
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/static/*", "/favicon.ico");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .httpBasic().and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/", "/home", "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            // @formatter:on
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TournamentApplication.class, args);
    }

    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}

@Component
class DummyDataCLR implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

        playerRepository.save(new Player("Johnny", "Bravo"));
        playerRepository.save(new Player("Marty", "McFly"));
        playerRepository.save(new Player("Barney", "Mead"));
        playerRepository.save(new Player("Sonny", "Crockett"));
        playerRepository.save(new Player("Laura", "Philips"));
        playerRepository.save(new Player("Monica", "King"));
        playerRepository.save(new Player("Kate", "Austen"));
        playerRepository.save(new Player("Sally", "Blue"));
        playerRepository.save(new Player("John", "Wick"));
        playerRepository.save(new Player("Jason", "Long"));
        playerRepository.save(new Player("Anna", "Grimm"));
        playerRepository.save(new Player("Lucy", "Prince"));
        playerRepository.save(new Player("Peter", "West"));
        playerRepository.save(new Player("Joseph", "North"));
        playerRepository.save(new Player("Matt", "East"));
        playerRepository.save(new Player("Jack", "South"));

        List<Long> playerIdList = new ArrayList<>();
        playerIdList.add(playerRepository.findByFirstNameAndLastName("Johnny", "Bravo").orElseThrow(InternalError::new).getId());
        playerIdList.add(playerRepository.findByFirstNameAndLastName("Marty", "McFly").orElseThrow(InternalError::new).getId());
        playerIdList.add(playerRepository.findByFirstNameAndLastName("Barney", "Mead").orElseThrow(InternalError::new).getId());
        playerIdList.add(playerRepository.findByFirstNameAndLastName("Lucy", "Prince").orElseThrow(InternalError::new).getId());

        CompetitionForm competitionForm = new CompetitionForm();
        competitionForm.setName("Competition");
        competitionForm.setPlayerIds(playerIdList);
        competitionForm.setNumberOfWinsRequired(3);
        competitionForm.setNumberOfPointsToWin(11);

        competitionService.createCompetition(competitionForm);
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CompetitionService competitionService;
}

