package inw.golf.testhelper;

import java.util.Arrays;

public class BoardTestHelper {

    public static boolean[][] CreateAllTrueBoard(int x, int y) {
        return CreateBoardWithAllCellsSetTo(x, y, true);
    }

    public static boolean[][] CreateAllFalseBoard(int x, int y) {
        return CreateBoardWithAllCellsSetTo(x, y, false);
    }

    private static boolean[][] CreateBoardWithAllCellsSetTo(int x, int y, boolean alive) {
        boolean[][] expectedResult = new boolean[x][y];
        for(boolean[] row : expectedResult)
            Arrays.fill(row, alive);
        return expectedResult;
    }
}
