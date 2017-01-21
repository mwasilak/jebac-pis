package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;

/**
 * Created by maciej on 21.01.17.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    Collection<Match> findByMatchPlayersPlayerName(@Param("q") String name);
}
