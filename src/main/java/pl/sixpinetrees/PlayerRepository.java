package pl.sixpinetrees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by maciej on 21.01.17.
 */
@RepositoryRestResource
interface PlayerRepository extends JpaRepository<Player, Long> {

    Collection<Player> findByName(@Param("q") String name);
}
