package inw.golf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import static inw.golf.testhelper.BoardTestHelper.CreateAllFalseBoard;
import static inw.golf.testhelper.BoardTestHelper.CreateAllTrueBoard;

public class GameBoardTest {

    @Test
    public void PlayBoard_With100PercentOddToBeLiveCell_AllCellsAreLiving() {
        int x = 10, y = 10;
        boolean[][] expectedResult = CreateAllTrueBoard(x, y);
        GameBoard sut = new GameBoard(x,y,1);

        boolean[][] board = sut.getPlayBoard();

        Assert.assertArrayEquals(expectedResult, board);
    }

    @Test
    public void PlayBoard_With0PercentOddToBeLiveCell_AllCellsAreLiving() {
        int x = 10, y = 10;
        boolean[][] expectedResult = CreateAllFalseBoard(x, y);
        GameBoard sut = new GameBoard(x,y,0);

        boolean[][] board = sut.getPlayBoard();

        Assert.assertArrayEquals(expectedResult, board);
    }

    @Ignore ("flaky by design") @Test
    public void PlayBoard_With50PercentOddToBeLiveCell_AboutHalfOfTheCellsAreLiving() {
        int x = 10, y = 10;
        GameBoard sut = new GameBoard(x,y,0.5);

        boolean[][] board = sut.getPlayBoard();

        int livingCells = CountAliveCells(board);
        Assert.assertTrue(livingCells < 0.55*x*y);
        Assert.assertTrue(livingCells > 0.45*x*y);
    }

    private int CountAliveCells(boolean[][] board) {
        int sum = 0;
        for(boolean[] row : board)
            sum += CountLivingCellsIn(row);
        return sum;
    }

    private int CountLivingCellsIn(boolean[] row) {
        int rowCount = 0;
        for(boolean cell : row) {
            if(cell)
                rowCount++;
        }
        return  rowCount;
    }
}