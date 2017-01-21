package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collection;

/**
 * Created by maciej on 21.01.17.
 */
@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional
    public void registerPlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Collection<Player> getPlayers() {
        return playerRepository.findAll();
    }
}
