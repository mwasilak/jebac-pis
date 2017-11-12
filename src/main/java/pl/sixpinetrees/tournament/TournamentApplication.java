package pl.sixpinetrees.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.Round;
import pl.sixpinetrees.tournament.domain.Player;
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
        playerRepository.save(new Player("Barney", "Stinson"));
        playerRepository.save(new Player("Sonny", "Crockett"));
        playerRepository.save(new Player("Laura", "Roslin"));
        playerRepository.save(new Player("Cersei", "Lannister"));
        playerRepository.save(new Player("Kate", "Austen"));
        playerRepository.save(new Player("Sally", "Blue"));
        playerRepository.save(new Player("John", "Wick"));
        playerRepository.save(new Player("Jason", "Bourne"));
        playerRepository.save(new Player("Anna", "Grimm"));
        playerRepository.save(new Player("Lucy", "Prince"));
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

}

