package se.ms.gameoflife;

import se.ms.gameoflife.service.GameOfLifeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeServiceTest {

    private GameOfLifeService gameOfLifeService;

    @BeforeEach
    public void setUp() {
        gameOfLifeService = new GameOfLifeService(10, 10);
    }

    @Test
    public void testUnderpopulationRule() {
        gameOfLifeService.setCellState(2, 2, true);
        gameOfLifeService.transitionToNextGeneration();
        assertFalse(gameOfLifeService.getBoard()[2][2],
                "Any live cell with fewer than two live neighbours dies, as if by underpopulation.");
    }

    @Test
    public void testSurvivalRule() {
        gameOfLifeService.setCellState(2, 2, true);
        gameOfLifeService.setCellState(2, 1, true);
        gameOfLifeService.setCellState(2, 3, true);
        gameOfLifeService.transitionToNextGeneration();
        assertTrue(gameOfLifeService.getBoard()[2][2],
                "Any live cell with two or three live neighbours lives on to the next generation.");
    }

    @Test
    public void testOverpopulationRule() {
        gameOfLifeService.setCellState(2, 2, true);
        gameOfLifeService.setCellState(2, 1, true);
        gameOfLifeService.setCellState(2, 3, true);
        gameOfLifeService.setCellState(1, 2, true);
        gameOfLifeService.setCellState(3, 2, true);
        gameOfLifeService.transitionToNextGeneration();
        assertFalse(gameOfLifeService.getBoard()[2][2],
                "Any live cell with more than three live neighbours dies, as if by overpopulation.");
    }

    @Test
    public void testReproductionRule() {
        gameOfLifeService.setCellState(1, 1, true);
        gameOfLifeService.setCellState(1, 2, true);
        gameOfLifeService.setCellState(2, 1, true);
        gameOfLifeService.transitionToNextGeneration();
        assertTrue(gameOfLifeService.getBoard()[2][2],
                "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
    }
}
