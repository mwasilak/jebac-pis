package pl.sixpinetrees.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.RegistrationForm;
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
    public Long registerPlayer(RegistrationForm registrationForm) {

        Player player = new Player(registrationForm.getFirstName(), registrationForm.getLastName());
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
