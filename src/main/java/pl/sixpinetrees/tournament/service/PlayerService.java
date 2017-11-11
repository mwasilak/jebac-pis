package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;

import java.util.Collection;

public interface PlayerService {

    Long registerPlayer(Player player);
    Collection<Player> getPlayers();
    Player getPlayer(Long id);

}
