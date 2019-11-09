package inw.golf;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayBoardTest {

    @Test
    public void PlayBoard_With100PercentOddToBeLiveCell_AllCellsAreLiving() {
        int x = 10, y = 10;
        boolean[][] expectedResult = CreateAllTrueBoard(x, y);
        PlayBoard sut = new PlayBoard(x,y,1);

        boolean[][] board = sut.getPlayBoard();

        Assert.assertArrayEquals(expectedResult, board);
    }

    @Test
    public void PlayBoard_With0PercentOddToBeLiveCell_AllCellsAreLiving() {
        int x = 10, y = 10;
        boolean[][] expectedResult = CreateAllFalseBoard(x, y);
        PlayBoard sut = new PlayBoard(x,y,0);

        boolean[][] board = sut.getPlayBoard();

        Assert.assertArrayEquals(expectedResult, board);
    }

    private boolean[][] CreateAllTrueBoard(int x, int y) {
        return CreateBoardWithAllCellsSetTo(x, y, true);
    }

    private boolean[][] CreateAllFalseBoard(int x, int y) {
        return CreateBoardWithAllCellsSetTo(x, y, false);
    }

    private boolean[][] CreateBoardWithAllCellsSetTo(int x, int y, boolean alive) {
        boolean[][] expectedResult = new boolean[x][y];
        for(boolean[] row : expectedResult)
            Arrays.fill(row, alive);
        return expectedResult;
    }
}