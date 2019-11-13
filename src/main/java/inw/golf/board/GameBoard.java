package inw.golf.board;

import java.util.Random;

public class GameBoard {

    private final boolean[][] playBoard;
    public final Dimension dimension;

    public GameBoard(int x, int y, double oddOfCellToBeAlive) {
        this(new Dimension(x,y), oddOfCellToBeAlive);
     }

    public GameBoard(Dimension dim, double oddOfCellToBeAlive) {
        dimension = dim;
        playBoard = new boolean[dim.x][dim.y];
        Random rnd = new Random();
        for(int row = 0; row < dim.y; row++)
            for(int col = 0; col < dim.x; col++)
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

