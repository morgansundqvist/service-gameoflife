package se.ms.gameoflife;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import se.ms.gameoflife.controller.GameOfLifeController;
import se.ms.gameoflife.service.GameOfLifeService;

@WebMvcTest(GameOfLifeController.class)
public class GameOfLifeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameOfLifeService gameService;

    @Test
    public void testNewGame() throws Exception {
        mockMvc.perform(post("/game/new-game")
                .param("rows", "10")
                .param("columns", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("message").value("New game started"));
    }

}