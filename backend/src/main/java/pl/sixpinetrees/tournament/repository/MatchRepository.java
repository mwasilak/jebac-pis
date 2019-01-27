package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Collection<Match> findByPlayer1IdOrPlayer2Id(@Param("id1") Long player1Id, @Param("id2") Long player2Id);

    //@Query("SELECT m FROM Match m, Player p1, Player p2 WHERE (p1.firstName LIKE %?1 AND p1.lastName LIKE %?2) OR (p2.firstName LIKE %?1 AND p2.lastName LIKE %?2)")
    //Collection<Match> findByPlayerFirstNameAndLastName(@Param("fn") String firstName, @Param("ln") String lastName);

    Optional<Match> findByCompetitionIdAndBracketPosition(@Param("id") Long competitionId, @Param("bracketPosition") BracketPosition bracketPosition);
}
