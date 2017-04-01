package pl.sixpinetrees.tournament.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.service.MatchService;
import pl.sixpinetrees.tournament.service.PlayerService;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Project: tournament
 * Created by maciej on 26.01.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private MatchService matchService;

    @Test
    public void players() throws Exception {

        ArrayList<Player> a = new ArrayList<Player>();
        a.add(new Player("Johnny", "Bravo"));
        given(this.playerService.getPlayers())
                .willReturn(a);

        this.mvc.perform(get("/players").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());

    }

}