package pl.sixpinetrees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

        Stream.of("Maciek", "Seba", "Tomek", "Kacper")
                .forEach(name -> playerRepository.save(new Player(name)));

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
                    MatchPlayer matchPlayer1 = new MatchPlayer(match, playerRepository.findByName("Maciek").iterator().next());
                    matchPlayer1.setMatchPlayerRounds(matchPlayerRounds1);
                    MatchPlayer matchPlayer2 = new MatchPlayer(match, playerRepository.findByName("Seba").iterator().next());
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

@RepositoryRestResource
interface PlayerRepository extends JpaRepository<Player, Long> {

    Collection<Player> findByName(String name);
}

@RepositoryRestResource
interface MatchRepository extends JpaRepository<Match, Long> {

    Collection<Match> findByMatchPlayers_PlayerName(@Param("q") String name);

}