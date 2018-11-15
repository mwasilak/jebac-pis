package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.domain.dto.PlayerForm;
import pl.sixpinetrees.tournament.service.PlayerService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    public PlayerController() {
    }

    @GetMapping
    public Collection<Player> getPlayers() {

        return playerService.getPlayers();
    }

    @GetMapping("/{playerId}")
    public Player player(@PathVariable("playerId") long playerId) {

        return playerService.getPlayer(playerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/add")
    public ResponseEntity<?> processPlayerForm(@Valid @RequestBody PlayerForm playerForm) {

        Long id = playerService.addPlayer(playerForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.CREATED);
    }
}
