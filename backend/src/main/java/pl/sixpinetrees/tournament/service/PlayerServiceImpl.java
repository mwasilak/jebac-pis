package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.PlayerForm;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional
    public Long addPlayer(PlayerForm playerForm) {

        Player player = new Player(playerForm.getFirstName(), playerForm.getLastName());
        Player savedPlayer = playerRepository.save(player);
        return savedPlayer.getId();
    }

    @Override
    public Collection<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getPlayer(Long id) {
        return playerRepository.findById(id);
    }
}
