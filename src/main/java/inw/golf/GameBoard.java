package inw.golf;

import java.util.Random;

public class GameBoard {

    private final boolean[][] playBoard;
    public final Dimension dimension;

    public GameBoard(int x, int y, double oddOfCellToBeAlive) {
        dimension = new Dimension(x,y);
        playBoard = new boolean[x][y];
        Random rnd = new Random();
        for(int row = 0; row < y; row++)
            for(int col = 0; col < x; col++)
                playBoard[col][row] = (oddOfCellToBeAlive) > rnd.nextDouble();
     }

    GameBoard(GameBoard input) {

        playBoard = input.getPlayBoard();
        dimension = input.dimension;
    }

    GameBoard(boolean[][] board) {
        dimension = new Dimension(board.length, board[0].length);
        playBoard = board;
    }

    public boolean[][] getPlayBoard() {
        return this.playBoard.clone();
    }

}

