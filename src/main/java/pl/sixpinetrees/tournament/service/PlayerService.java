package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.RegistrationForm;

import java.util.Collection;
import java.util.Optional;

public interface PlayerService {

    Long registerPlayer(RegistrationForm registrationForm);
    Collection<Player> getPlayers();
    Optional<Player> getPlayer(Long id);

}
