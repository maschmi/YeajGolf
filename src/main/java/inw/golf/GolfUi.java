package inw.golf;

import inw.golf.gamerules.GameRuleFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GolfUi extends Application {

    private Timer timer = new Timer();

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws InterruptedException {

        Label heading = new Label("Game of life: Round 0");
        int canvasSize = 400;


        Canvas canvas = new Canvas(canvasSize, canvasSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameBoard board = new GameBoard(200,200,0.1);
        GameRound round = new GameRound(new GameRuleFactory().CreateDefaultRuleSet());
        List<GameBoard> boards = PlayGame(round, board, 100);
        drawBoard(gc, board, canvasSize);
        GridPane grid = new GridPane();

        grid.add(heading, 0,0);
        grid.add(canvas,0,1);
        Scene scene = new Scene(grid, 640, 480);

        stage.setScene(scene);

        this.timer.scheduleAtFixedRate(new TimerTask() {
            int rnd = 0;
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gc.clearRect(0,0,canvasSize,canvasSize);
                        drawBoard(gc, boards.get(rnd), canvasSize);
                        heading.setText("Game of life: Round "+rnd);
                        stage.setScene(scene);
                    }
                });
                rnd++;
                if(rnd >= 100)
                    rnd = 0;
            }
        }, 100,100);

        stage.show();
    }

    @Override
    public void stop() {
        this.timer.cancel();
    }


    private List<GameBoard> PlayGame(GameRound round, GameBoard board, int maxRounds) {
        ArrayList<GameBoard> result = new ArrayList<>();
        result.add(board);
        for(int i = 0; i < maxRounds; i++)
            result.add(round.Play(result.get(i)));
        return result;
    }


    private void drawBoard(GraphicsContext gc, GameBoard board, int canvasSize) {
        Dimension boardDim = board.dimension;
        boolean[][] currentState = board.getPlayBoard();
        int blockSize = canvasSize/boardDim.y;
        for(int row = 1; row <= boardDim.y; row++){
            for(int col = 1; col <= boardDim.x; col++) {
                if(currentState[col-1][row-1])
                    gc.fillRect(col * blockSize,row * blockSize,blockSize, blockSize);
            }
        }



    }
}
