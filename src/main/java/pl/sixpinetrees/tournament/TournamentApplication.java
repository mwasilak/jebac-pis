package pl.sixpinetrees.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.MatchPlayer;
import pl.sixpinetrees.tournament.domain.MatchPlayerRound;
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

        Stream.of("Semifinal 1", "Semifinal 2", "Final")
                .forEach(name -> matches.add(new Match(name)));

        playerRepository.findAll().forEach(System.out::println);

        matches.stream().filter(match -> match.getName().equals("Semifinal 1"))
                .findFirst().ifPresent(match -> {
                    ArrayList<MatchPlayerRound> matchPlayerRounds1 = new ArrayList<>();
                    matchPlayerRounds1.add(new MatchPlayerRound(1, 5));
                    matchPlayerRounds1.add(new MatchPlayerRound(2, 6));
                    matchPlayerRounds1.add(new MatchPlayerRound(3, 7));
                    ArrayList<MatchPlayerRound> matchPlayerRounds2 = new ArrayList<>();
                    matchPlayerRounds2.add(new MatchPlayerRound(1, 11));
                    matchPlayerRounds2.add(new MatchPlayerRound(2, 11));
                    matchPlayerRounds2.add(new MatchPlayerRound(3, 11));
                    ArrayList<MatchPlayer> matchPlayers = new ArrayList<>();
                    MatchPlayer matchPlayer1 = new MatchPlayer(match, playerRepository.findByFirstNameAndLastName("Johnny", "Bravo").iterator().next());
                    matchPlayer1.setMatchPlayerRounds(matchPlayerRounds1);
                    MatchPlayer matchPlayer2 = new MatchPlayer(match, playerRepository.findByFirstNameAndLastName("Marty", "McFly").iterator().next());
                    matchPlayer2.setMatchPlayerRounds(matchPlayerRounds2);
                    matchPlayers.add(matchPlayer1);
                    matchPlayers.add(matchPlayer2);
                    match.setMatchPlayers(matchPlayers);
                });

        matchRepository.save(matches);

    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

}

