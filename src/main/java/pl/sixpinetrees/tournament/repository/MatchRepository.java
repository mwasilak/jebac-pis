package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;

/**
 * Project: tournament
 * Created by maciej on 21.01.17.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    Collection<Match> findByName(@Param("nm") String name);

    Collection<Match> findByPlayer1IdOrPlayer2Id(@Param("id") Long id);

    @Query("SELECT m FROM Match m, Player p1, Player p2 WHERE (p1.firstName LIKE %?1 AND p1.lastName LIKE %?2) OR (p2.firstName LIKE %?1 AND p2.lastName LIKE %?2)")
    Collection<Match> findByPlayerFirstNameAndLastName(@Param("fn") String firstName, @Param("ln") String lastName);
}
