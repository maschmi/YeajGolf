package inw.golf.gamerules;

public final class StayAliveRule extends NeighborDependentRule implements GameRule {

    @Override
    public boolean Execute(Position currentPosition, boolean[][] board) {
        boolean currentState = IsAlive(currentPosition, board);
        if(!currentState)
            return false;

        int aliveNeighbors = CountAliveNeighbors(currentPosition, board);

        if(aliveNeighbors >= 2 && aliveNeighbors <= 3)
            return true;

        return true;
    }
}
