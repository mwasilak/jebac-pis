package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;

import java.util.Collection;
import java.util.Optional;

public interface PlayerService {

    Long registerPlayer(Player player);
    Collection<Player> getPlayers();
    Optional<Player> getPlayer(Long id);

}
