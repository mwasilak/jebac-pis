package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sixpinetrees.tournament.domain.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
