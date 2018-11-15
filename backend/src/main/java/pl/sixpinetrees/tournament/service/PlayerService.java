package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.PlayerForm;

import java.util.Collection;
import java.util.Optional;

public interface PlayerService {

    Long addPlayer(PlayerForm playerForm);
    Collection<Player> getPlayers();
    Optional<Player> getPlayer(Long id);

}
