package inw.golf.gamerules;

import java.util.List;

public abstract class NeighborDependentRule {

    protected static int CountAliveNeighbors(Position currentPosition, boolean[][] board) {
        int sum = 0;
        List<Integer> cols = List.of(currentPosition.x - 1,  currentPosition.x, currentPosition.x + 1 );
        List<Integer> rows = List.of(currentPosition.y - 1, currentPosition.y ,currentPosition.y + 1 );

        for (Integer col : cols) {
            for (Integer row : rows) {
                sum += IsAlive(col, row, board) ? 1 : 0;
            }
        }

        return sum - (IsAlive(currentPosition,board) ? 1 : 0);
    }

    private static boolean IsAlive(int col, int row, boolean[][] board) {
        try {
            return board[col][row];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    protected static boolean IsAlive(Position currentPosition, boolean[][] board) {
        return IsAlive(currentPosition.x, currentPosition.y, board);
    }
}
