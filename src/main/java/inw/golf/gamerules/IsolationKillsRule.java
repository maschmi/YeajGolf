package inw.golf.gamerules;

public final class IsolationKillsRule extends NeighborDependentRule implements GameRule {

    @Override
    public boolean Execute(Position currentPosition, boolean[][] board) {

        boolean currentState = IsAlive(currentPosition, board);
        if(currentState && CountAliveNeighbors(currentPosition,board) < 2)
            return false;

        return currentState;
    }
}
