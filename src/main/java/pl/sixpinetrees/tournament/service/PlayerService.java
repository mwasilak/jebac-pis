package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;

import java.util.Collection;

/**
 * Project: tournament
 * Created by maciej on 21.01.17.
 */
public interface PlayerService {

    Long registerPlayer(Player player);
    Collection<Player> getPlayers();
    Player getPlayer(Long id);

}
