package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sixpinetrees.tournament.domain.Competition;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {


    <T> Optional<T> findById(Long Id, Class<T> type);
}
