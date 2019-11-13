package inw.golf.board;

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
        boolean currentState = currentBoard[position.x][position.y];
        for (GameRule rule : gameRules) {
            boolean newState = rule.Execute(position, currentBoard);
            if(newState != currentState)
                return newState;
        }
        return currentState;
    }

}
