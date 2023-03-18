package se.ms.gameoflife;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import se.ms.gameoflife.controller.GameOfLifeController;
import se.ms.gameoflife.service.GameOfLifeService;

@WebMvcTest(GameOfLifeController.class)
public class GameOfLifeControllerNotStartedTests {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private GameOfLifeService gameService;

    @Test
    public void testSetCellState_GameNotStarted() throws Exception {

        mockMvc.perform(post("/game/set-cell-state")
                .param("row", "2")
                .param("column", "2")
                .param("alive", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("message").value("Game not started"));
    }

    @Test
    public void testBoard_GameNotStarted() throws Exception {

        mockMvc.perform(get("/game/board")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("message").value("Game not started"));
    }

    @Test
    public void testTransitionToNextGeneration_GameNotStarted() throws Exception {

        mockMvc.perform(post("/game/transition-to-next-generation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("message").value("Game not started"));
    }
}