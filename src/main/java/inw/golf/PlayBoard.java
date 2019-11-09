package inw.golf;

import java.util.Random;

public class PlayBoard {

    private final boolean[][] playBoard;

    public PlayBoard(int x, int y, double oddOfCellToBeLiving) {
        playBoard = new boolean[x][y];
        Random rnd = new Random();
        for(int row = 0; row < y; row++)
            for(int col = 0; col < x; col++)
                playBoard[row][col] = (oddOfCellToBeLiving) > rnd.nextDouble();
     }

     PlayBoard(PlayBoard input) {
        playBoard = input.getPlayBoard();
     }

     public boolean[][] getPlayBoard() {
         return this.playBoard;
     }
}
