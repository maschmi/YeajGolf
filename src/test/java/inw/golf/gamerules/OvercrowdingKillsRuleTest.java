package inw.golf.gamerules;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class OvercrowdingKillsRuleTest {

    @Test
    public void CellWithMoreThenThreeNeighbors_Dies() {
        GameRule sut = new OvercrowdingKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,true},
                {true,true,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }

    @Test
    public void CellWithThreeNeighbors_StaysAlive() {
        GameRule sut = new OvercrowdingKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,true},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isTrue();
    }

    @Test
    public void DeadCellWithMoreThenThreeNeighbors_StaysDead() {
        GameRule sut = new OvercrowdingKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,false,true},
                {true,false,true},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }
}