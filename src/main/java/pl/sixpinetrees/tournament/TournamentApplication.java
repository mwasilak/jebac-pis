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

        List<Match> matches = new ArrayList<>();

        playerRepository.save(new Player("Johnny", "Bravo"));
        playerRepository.save(new Player("Marty", "McFly"));
        playerRepository.save(new Player("Barney", "Stinson"));
        playerRepository.save(new Player("Sonny", "Crockett"));

        Match match1 = new Match("Semifinal 1", 1, 1);
        ArrayList<Round> roundsList1 = new ArrayList<>();
        roundsList1.add(new Round(1, 5, 11));
        roundsList1.add(new Round(2, 11, 9));
        roundsList1.add(new Round(3, 7, 11));
        match1.setPlayer1(playerRepository.findByFirstNameAndLastName("Johnny", "Bravo").iterator().next());
        match1.setPlayer2(playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next());
        match1.setRounds(roundsList1);


        Match match2 = new Match("Semifinal 2", 1, 2);
        ArrayList<Round> roundsList2 = new ArrayList<>();
        roundsList2.add(new Round(1, 12, 10));
        roundsList2.add(new Round(2, 4, 11));
        roundsList2.add(new Round(3, 11, 7));
        match2.setPlayer1(playerRepository.findByFirstNameAndLastName("Barney", "Stinson").iterator().next());
        match2.setPlayer2(playerRepository.findByFirstNameAndLastName("Sonny", "Crockett").iterator().next());
        match2.setRounds(roundsList2);

        Match match3 = new Match("Final", 2, 1);
        ArrayList<Round> roundsList3 = new ArrayList<>();
        roundsList3.add(new Round(1, 9, 11));
        roundsList3.add(new Round(2, 11, 8));
        roundsList3.add(new Round(3, 11, 2));
        match3.setPlayer1(playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next());
        match3.setPlayer2(playerRepository.findByFirstNameAndLastName("Barney", "Stinson").iterator().next());
        match3.setRounds(roundsList3);

        matches.stream().filter(match -> match.getName().equals("Practice"))
                .findFirst().ifPresent(match -> match.setPlayer1(playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next()));

        matchRepository.save(matches);

        playerRepository.findAll().forEach(System.out::println);
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

}

