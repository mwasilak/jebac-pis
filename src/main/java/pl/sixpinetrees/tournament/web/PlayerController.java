package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public Collection<Player> getPlayers() {

        return playerRepository.findAll();
    }

    @GetMapping("/{playerId}")
    public Player player(@PathVariable("playerId") long playerId) {

        return playerRepository.findById(playerId).orElseThrow( () -> new NotFoundException("Player with id " + playerId + " not found") );
    }

    @GetMapping("match/{matchId}")
    public Map<Long, Player> getPlayersByMatchId(@PathVariable("matchId") Long matchId) {

        return playerRepository.findAllByMatchId(matchId).stream().collect(Collectors.toMap(Player::getId, v -> v));
    }

    @GetMapping("competition/{competitionId}")
    public Map<Long, Player> getPlayersByCompetitionId(@PathVariable("competitionId") Long competitionId) {

        return playerRepository.findAllByCompetitionId(competitionId).stream().collect(Collectors.toMap(Player::getId, v -> v));
    }
}
