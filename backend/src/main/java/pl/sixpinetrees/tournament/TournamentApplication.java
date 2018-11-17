package pl.sixpinetrees.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.Competition;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.repository.CompetitionRepository;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TournamentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TournamentApplication.class, args);
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

        List<Player> playerList = new ArrayList<>();
        playerList.addAll(playerRepository.findByFirstNameAndLastName("Johnny", "Bravo"));
        playerList.addAll(playerRepository.findByFirstNameAndLastName("Marty", "McFly"));
        playerList.addAll(playerRepository.findByFirstNameAndLastName("Barney", "Mead"));
        playerList.addAll(playerRepository.findByFirstNameAndLastName("Lucy", "Prince"));

        competitionRepository.save(new Competition("Competition",  playerList));
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CompetitionRepository competitionRepository;
}

