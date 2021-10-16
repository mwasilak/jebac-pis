package pl.sixpinetrees.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.CompetitionForm;
import pl.sixpinetrees.tournament.repository.PlayerRepository;
import pl.sixpinetrees.tournament.auth.UserRegistrationRequest;
import pl.sixpinetrees.tournament.auth.UserService;
import pl.sixpinetrees.tournament.service.CompetitionService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class TournamentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TournamentApplication.class, args);
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

        userService.addUser(new UserRegistrationRequest("jbravo", "password", "Johnny", "Bravo"));
        userService.addUser(new UserRegistrationRequest("mmcfly", "password", "Marty", "McFly"));
        userService.addUser(new UserRegistrationRequest("bmead", "password", "Barney", "Mead"));
        userService.addUser(new UserRegistrationRequest("scrockett", "password", "Sonny", "Crockett"));
        userService.addUser(new UserRegistrationRequest("lphilips", "password", "Laura", "Philips"));
        userService.addUser(new UserRegistrationRequest("mking", "password", "Monica", "King"));
        userService.addUser(new UserRegistrationRequest("kausten", "password", "Kate", "Austen"));
        userService.addUser(new UserRegistrationRequest("sblue", "password", "Sally", "Blue"));
        userService.addUser(new UserRegistrationRequest("jwick", "password", "John", "Wick"));
        userService.addUser(new UserRegistrationRequest("jlong", "password", "Jason", "Long"));
        userService.addUser(new UserRegistrationRequest("agrimm", "password", "Anna", "Grimm"));
        userService.addUser(new UserRegistrationRequest("lprince", "password", "Lucy", "Prince"));
        userService.addUser(new UserRegistrationRequest("pwest", "password", "Peter", "West"));
        userService.addUser(new UserRegistrationRequest("jnorth", "password", "Joseph", "North"));
        userService.addUser(new UserRegistrationRequest("meast", "password", "Matt", "East"));
        userService.addUser(new UserRegistrationRequest("jsouth", "password", "Jack", "South"));

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

    @Autowired
    private UserService userService;
}

