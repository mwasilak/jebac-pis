package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Match;

import java.util.Collection;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Collection<Match> findByPlayer1IdOrPlayer2Id(@Param("id1") Long player1Id, @Param("id2") Long player2Id);

    Optional<Match> findByCompetitionIdAndBracketPosition(@Param("id") Long competitionId, @Param("bracketPosition") BracketPosition bracketPosition);

    Collection<Match> findByCompetitionId(@Param("id") Long competitionId);

}
