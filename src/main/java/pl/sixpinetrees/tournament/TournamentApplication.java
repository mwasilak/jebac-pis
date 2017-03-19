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
import java.util.stream.Stream;

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

        Stream.of("Semifinal 1", "Semifinal 2", "Final", "Practice")
                .forEach(name -> matches.add(new Match(name)));

        matches.stream().filter(match -> match.getName().equals("Semifinal 1"))
                .findFirst().ifPresent(match -> {
            ArrayList<Round> roundsList = new ArrayList<>();
            roundsList.add(new Round(1, 5, 11));
            roundsList.add(new Round(2, 11, 9));
            roundsList.add(new Round(3, 7, 11));
            match.setPlayer1(playerRepository.findByFirstNameAndLastName("Johnny", "Bravo").iterator().next());
            match.setPlayer2(playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next());
            match.setRounds(roundsList);
                });

        matches.stream().filter(match -> match.getName().equals("Semifinal 2"))
                .findFirst().ifPresent(match -> {
            ArrayList<Round> roundsList = new ArrayList<>();
            roundsList.add(new Round(1, 12, 10));
            roundsList.add(new Round(2, 4, 11));
            roundsList.add(new Round(3, 11, 7));
            match.setPlayer1(playerRepository.findByFirstNameAndLastName("Barney", "Stinson").iterator().next());
            match.setPlayer2(playerRepository.findByFirstNameAndLastName("Sonny", "Crockett").iterator().next());
            match.setRounds(roundsList);
        });

        matches.stream().filter(match -> match.getName().equals("Final"))
                .findFirst().ifPresent(match -> {
            ArrayList<Round> roundsList = new ArrayList<>();
            roundsList.add(new Round(1, 9, 11));
            roundsList.add(new Round(2, 11, 8));
            roundsList.add(new Round(3, 11, 2));
            match.setPlayer1(playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next());
            match.setPlayer2(playerRepository.findByFirstNameAndLastName("Barney", "Stinson").iterator().next());
            match.setRounds(roundsList);
        });

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

