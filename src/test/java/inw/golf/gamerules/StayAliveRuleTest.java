package inw.golf.gamerules;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class StayAliveRuleTest {

    @Test
    public void AliveCellWithTwoNeighbors_StaysAlive() {
        GameRule sut = new StayAliveRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,false},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isTrue();
    }

    @Test
    public void AliveCellWithThreeNeighbors_StaysAlive() {
        GameRule sut = new StayAliveRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,true},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isTrue();
    }

    @Test
    public void DeadAliveCellWithThreeNeighbors_StaysDead() {
        GameRule sut = new StayAliveRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,false,true},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }
}