package inw.golf.gamerules;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class IsolationKillsRuleTest {

    @Test
    public void CellWithLessThenTwoNeighbors_Dies() {
        GameRule sut = new IsolationKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,false},
                {false,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }

    @Test
    public void CellWithTwoNeighbors_Survives() {
        GameRule sut = new IsolationKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,false},
                {false,false,true},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isTrue();
    }

    @Test
    public void DeadCellWithMoreThenTwoNeighbors_StaysDead() {
        GameRule sut = new IsolationKillsRule();
        boolean[][] testBoard = {
                {true,false,false},
                {true,false,false},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }


}
