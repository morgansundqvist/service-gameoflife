package se.ms.gameoflife.service;

public class GameOfLifeService {

    private boolean[][] board;
    private int boardHeight;
    private int boardWidth;

    public GameOfLifeService(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        this.board = new boolean[this.boardHeight][this.boardWidth];
    }

    public void setCellState(int row, int column, boolean isAlive) {
        this.board[row][column] = isAlive;
    }

    public void transitionToNextGeneration() {
        boolean[][] nextGenerationBoard = new boolean[this.boardHeight][this.boardWidth];
        for (int rowIndex = 0; rowIndex < nextGenerationBoard.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < nextGenerationBoard[rowIndex].length; columnIndex++) {
                boolean cellIsAlive = this.board[rowIndex][columnIndex];
                int numberOfAliveNeighborCells = 0;
                for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
                    for (int j = columnIndex - 1; j < columnIndex + 1; j++) {
                        if (i >= 0 && j >= 0 && i < this.boardHeight && j < this.boardWidth && i != rowIndex
                                && j != columnIndex) {
                            if (this.board[i][j]) {
                                numberOfAliveNeighborCells++;
                            }
                        }
                    }

                }
                if (cellIsAlive && numberOfAliveNeighborCells < 2) {
                    nextGenerationBoard[rowIndex][columnIndex] = false;
                }
            }
        }

        this.board = nextGenerationBoard;
    }

    public boolean[][] getBoard() {
        return this.board;
    }

}
