package se.ms.gameoflife;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import se.ms.gameoflife.controller.GameOfLifeController;
import se.ms.gameoflife.service.GameOfLifeService;

@WebMvcTest(GameOfLifeController.class)
public class GameOfLifeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private GameOfLifeService gameService;

    @BeforeEach
    public void setup() {
        gameService.startGame(10, 10);
    }

    @Test
    public void testNewGame() throws Exception {
        mockMvc.perform(post("/game/new-game")
                .param("rows", "10")
                .param("columns", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("message").value("New game started"));
    }

    @Test
    public void testSetCellState() throws Exception {
        
        when(gameService.getBoard()).thenReturn(new boolean[5][5]);

        mockMvc.perform(post("/game/set-cell-state")
                .param("row", "2")
                .param("column", "2")
                .param("alive", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Cell state updated"));
    }

    @Test
    public void testGetBoard() throws Exception {
        boolean[][] board = new boolean[][] {
                { false, false, false },
                { false, true, false },
                { false, false, false }
        };
        when(gameService.getBoard()).thenReturn(board);

        mockMvc.perform(get("/game/board")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("OK"))
                .andExpect(jsonPath("data").isArray())
                .andExpect(jsonPath("data[1][1]").value(true));
    }

    @Test
    public void testTransitionToNextGeneration() throws Exception {
        boolean[][] board = new boolean[][] {
                { false, false, false },
                { false, true, false },
                { false, false, false }
        };
        when(gameService.getBoard()).thenReturn(board);

        mockMvc.perform(post("/game/transition-to-next-generation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("OK"))
                .andExpect(jsonPath("data").isArray())
                .andExpect(jsonPath("data[1][1]").value(true));
    }

}