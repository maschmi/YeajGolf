package inw.golf.gamerules;

public interface GameRule {
    boolean Execute(Position currentPosition, boolean[][] board);
}
