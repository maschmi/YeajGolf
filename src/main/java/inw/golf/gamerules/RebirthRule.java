package inw.golf.gamerules;

public final class RebirthRule extends NeighborDependentRule implements GameRule {

    @Override
    public boolean Execute(Position currentPosition, boolean[][] board) {
        boolean currentState = IsAlive(currentPosition, board);
        if(!currentState &&  CountAliveNeighbors(currentPosition, board) == 3)
            return true;

        return currentState;
    }
}
