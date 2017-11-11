package pl.sixpinetrees.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.sixpinetrees.tournament.domain.Player;

import java.util.Collection;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Collection<Player> findByFirstNameAndLastName(@Param("fn") String firstName, @Param("ln") String lastName);
}
