package se.ms.gameoflife.service;

public class GameOfLifeService {

    private boolean[][] board;
    private int boardHeight;
    private int boardWidth;

    public void startGame(int boardHeight, int boardWidth) {
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
                boolean cellIsAlive = this.isCellAlive(rowIndex, columnIndex);
                int numberOfAliveNeighborCells = 0;
                numberOfAliveNeighborCells = calculateAliveNeighborCells(rowIndex, columnIndex,
                        numberOfAliveNeighborCells);
                // Underpopulation rule
                if (cellIsAlive && numberOfAliveNeighborCells < 2) {
                    nextGenerationBoard[rowIndex][columnIndex] = false;
                    // Survival rule
                } else if (cellIsAlive && numberOfAliveNeighborCells >= 2 && numberOfAliveNeighborCells <= 3) {
                    nextGenerationBoard[rowIndex][columnIndex] = true;
                    // Over population rule
                } else if (cellIsAlive && numberOfAliveNeighborCells > 3) {
                    nextGenerationBoard[rowIndex][columnIndex] = false;
                    // Reproduction rule
                } else if (!cellIsAlive && numberOfAliveNeighborCells == 3) {
                    nextGenerationBoard[rowIndex][columnIndex] = true;
                }
            }
        }

        this.board = nextGenerationBoard;
    }

    private int calculateAliveNeighborCells(int rowIndex, int columnIndex, int numberOfAliveNeighborCells) {
        for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
            for (int j = columnIndex - 1; j <= columnIndex + 1; j++) {
                if (isCellOnBoard(i, j) && isCellAlive(i, j)
                        && isNotCurrentCell(rowIndex, columnIndex, i, j)) {
                    numberOfAliveNeighborCells++;
                }
            }

        }
        return numberOfAliveNeighborCells;
    }

    private boolean isNotCurrentCell(int rowIndex, int columnIndex, int i, int j) {
        return !(i == rowIndex
                && j == columnIndex);
    }

    private boolean isCellOnBoard(int i, int j) {
        return i >= 0 && j >= 0 && i < this.boardHeight && j < this.boardWidth;
    }

    private boolean isCellAlive(int rowIndex, int columnIndex) {
        return this.board[rowIndex][columnIndex];
    }

    public boolean[][] getBoard() {
        return this.board;
    }

}
