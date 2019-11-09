package inw.golf;

import inw.golf.gamerules.GameRule;
import inw.golf.gamerules.Position;

import java.util.Arrays;
import java.util.List;

public class GameRound {

    private List<GameRule> gameRules;

    public GameRound(GameRule... rules) {
        gameRules = Arrays.asList(rules);
    }

    public GameBoard Play(GameBoard board) {
        boolean[][] currentBoard = board.getPlayBoard();
        boolean[][] newBoard = new boolean[board.dimension.x][board.dimension.y];
        Dimension boardDimensions = board.dimension;
        for(int row = 0; row < boardDimensions.y; row++)
            for(int col = 0; col < boardDimensions.x; col++)
                newBoard[col][row] = ExecuteRules(new Position(col, row), currentBoard);

        return new GameBoard(newBoard);
    }

    private boolean ExecuteRules(Position position, boolean[][] currentBoard) {
        return gameRules
                .stream()
                .map(rule -> rule.Execute(position, currentBoard))
                .reduce(true, (a, b) -> a && b);
    }

}
