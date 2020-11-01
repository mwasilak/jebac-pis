package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.Competition;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("select distinct c from Competition c join Match m on c.id = m.competitionId where m.id = :matchId")
    Optional<Competition> findByMatchId(@Param("matchId") Long matchId);
}
