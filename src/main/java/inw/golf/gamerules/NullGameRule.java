package inw.golf.gamerules;

public final class NullGameRule implements GameRule {
    @Override
    public boolean Execute(Position currentPosition, boolean[][] board) {
        return board[currentPosition.x][currentPosition.y];
    }
}

