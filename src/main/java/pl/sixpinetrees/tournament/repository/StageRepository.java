package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sixpinetrees.tournament.domain.Stage;

/**
 * Project: tournament
 * Created by maciej on 21.01.17.
 */
public interface StageRepository extends JpaRepository<Stage, Long> {

}
