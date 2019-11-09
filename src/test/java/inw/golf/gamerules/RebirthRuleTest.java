package inw.golf.gamerules;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RebirthRuleTest {

    @Test
    public void CellWithThreeNeighbors_IsReborn() {
        GameRule sut = new RebirthRule();
        boolean[][] testBoard = {
            {true,false,false},
            {false,false,false},
            {true,true,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isTrue();
    }

    @Test
    public void CellWithTwoNeighbors_StaysDead() {
        GameRule sut = new RebirthRule();
        boolean[][] testBoard = {
                {true,false,false},
                {false,false,false},
                {true,false,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }

    @Test
    public void CellWithFourNeighbors_StaysDead() {
        GameRule sut = new RebirthRule();
        boolean[][] testBoard = {
                {true,false,false},
                {true,false,false},
                {true,true,false},
        };
        boolean result = sut.Execute(new Position(1,1), testBoard);

        assertThat(result).isFalse();
    }

    @Test
    public void CornerCellWithThreeNeighbors_IsReborn() {
        GameRule sut = new RebirthRule();
        boolean[][] testBoard = {
                {true,false,false},
                {true,true,true},
                {true,true,false},
        };
        boolean result = sut.Execute(new Position(2,2), testBoard);

        assertThat(result).isTrue();
    }

}