package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;

import java.util.Collection;

/**
 * Created by maciej on 21.01.17.
 */
public interface PlayerService {

    void registerPlayer(Player player);
    Collection<Player> getPlayers();

}
