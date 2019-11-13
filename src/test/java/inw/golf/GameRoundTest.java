package inw.golf;

import inw.golf.board.GameBoard;
import inw.golf.board.GameRound;
import inw.golf.gamerules.GameRuleFactory;
import inw.golf.gamerules.InverterGameRule;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRoundTest {

    @Test
    public void PlayRound_WithOneInverterRule_AllCellsAreInverted() {
        int x = 10, y = 10;
        GameBoard gb = new GameBoard(x,y,0.3);
        GameRound gr = new GameRound(new InverterGameRule());
        boolean[][] oldState = gb.getPlayBoard();
        boolean[][] newState = gr.Play(gb).getPlayBoard();

        SoftAssertions softly = new SoftAssertions();
        VerfiyCellStatesAreInverted(x, y, oldState, newState, softly);
        softly.assertAll();
    }

    //make sure all rules are run on current state and not on updated state
    @Test
    public void PlayRound_WithTwoInverterRules_AllCellsAreInverted() {
        int x = 10, y = 10;
        GameBoard gb = new GameBoard(x,y,0.3);
        GameRound gr = new GameRound(new InverterGameRule(), new InverterGameRule());
        boolean[][] oldState = gb.getPlayBoard();
        boolean[][] newState = gr.Play(gb).getPlayBoard();

        SoftAssertions softly = new SoftAssertions();
        VerfiyCellStatesAreInverted(x, y, oldState, newState, softly);
        softly.assertAll();
    }


    @Test
    public void PlayRound_WithDefaultRuleSet_RebirthIsSuccessful() {
        boolean[][] testBoard = {
                {true,false,false},
                {true,false,false},
                {true,false,false},
        };
        boolean result = playRound(testBoard);
        assertThat(result).isTrue();
    }

    @Test
    public void PlayRound_WithDefaultRuleSet_IsolationKillsIsSuccessful() {
        boolean[][] testBoard = {
                {true,false,false},
                {false,true,false},
                {false,false,false},
        };

        boolean result = playRound(testBoard);
        assertThat(result).isFalse();
    }

    @Test
    public void PlayRound_WithDefaultRuleSet_StayAliveIsSuccessful() {
        boolean[][] testBoard = {
                {true,false,false},
                {true,true,false},
                {false,false,false},
        };

        boolean result = playRound(testBoard);
        assertThat(result).isTrue();
    }

    @Test
    public void PlayRound_WithDefaultRuleSet_OvercrowdingKillsIsSuccessful() {
        boolean[][] testBoard = {
                {true,false,false},
                {true,true,false},
                {true,true,false},
        };

        boolean result = playRound(testBoard);
        assertThat(result).isFalse();
    }

    private boolean playRound(boolean[][] testBoard) {
        GameBoard board = new GameBoard(testBoard);
        GameRound round = new GameRound(new GameRuleFactory().CreateDefaultRuleSet());
        return round.Play(board).getPlayBoard()[1][1];
    }

    private void VerfiyCellStatesAreInverted(int x, int y, boolean[][] oldState, boolean[][] newState, SoftAssertions softly) {
        for(int row = 0; row < y; row++)
            for(int col = 0; col < x; col++)
                softly.assertThat(newState[col][row])
                .as("("+col+","+row+") was not inverted")
                        .isEqualTo(!oldState[col][row]);
    }
}