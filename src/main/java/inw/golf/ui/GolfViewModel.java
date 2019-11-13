package inw.golf.ui;

import inw.golf.board.Dimension;
import inw.golf.board.GameBoard;
import inw.golf.board.GameRound;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GolfViewModel {
    private final Stage stage;
    public GameBoard board;
    public GameRound round;
    public ArrayList<GameBoard> boards;
    public Dimension boardDimensions;
    public int maxRounds;


    public GolfViewModel(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
