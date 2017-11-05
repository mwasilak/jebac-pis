package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional
    public Long registerPlayer(Player player) {

        System.out.println(player);
        Player savedPlayer = playerRepository.save(player);
        System.out.println(savedPlayer);
        return savedPlayer.getId();
    }

    @Override
    public Collection<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findOne(id);
    }
}
