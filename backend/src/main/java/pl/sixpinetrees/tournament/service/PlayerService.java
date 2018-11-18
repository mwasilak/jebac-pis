package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.PlayerForm;

public interface PlayerService {

    Long addPlayer(PlayerForm playerForm);
}
