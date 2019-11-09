package inw.golf.gamerules;

public class InverterGameRule implements GameRule {
    @Override
    public boolean Execute(Position currentPosition, boolean[][] board) {
        boolean cell = board[currentPosition.x][currentPosition.y];
        return !cell;
    }
}
