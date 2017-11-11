package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sixpinetrees.tournament.domain.Competition;

/**
 * Project: tournament
 * Created by maciej on 21.01.17.
 */
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
