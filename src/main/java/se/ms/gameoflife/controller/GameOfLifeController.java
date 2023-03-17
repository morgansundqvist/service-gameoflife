package se.ms.gameoflife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.ms.gameoflife.service.GameOfLifeService;
import se.ms.gameoflife.types.ApiResponse;

@RestController
@RequestMapping("/game")
public class GameOfLifeController {

    @Autowired
    private GameOfLifeService gameService;

    @PostMapping("/new-game")
    public ResponseEntity<ApiResponse<String>> newGame(@RequestParam int rows, @RequestParam int columns) {
        gameService = new GameOfLifeService();
        gameService.startGame(rows, columns);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<String>(null, "New game started"));
    }

    @PostMapping("/set-cell-state")
    public ResponseEntity<ApiResponse<String>> setCellState(@RequestParam int row, @RequestParam int column,
            @RequestParam boolean alive) {
        if (gameService == null || gameService.getBoard() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<String>(null, "Game not started"));
        }
        gameService.setCellState(row, column, alive);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<String>(null, "Cell state updated"));
    }

    @GetMapping("/board")
    public ResponseEntity<ApiResponse<boolean[][]>> getBoard() {
        if (gameService == null || gameService.getBoard() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<boolean[][]>(null, "Game not started"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<boolean[][]>(gameService.getBoard(), "OK"));
    }

    @PostMapping("/transition-to-next-generation")
    public ResponseEntity<ApiResponse<boolean[][]>> transitionToNextGeneration() {
        if (gameService == null || gameService.getBoard() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<boolean[][]>(null, "Game not started"));
        }
        gameService.transitionToNextGeneration();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<boolean[][]>(gameService.getBoard(), "OK"));
    }
}
