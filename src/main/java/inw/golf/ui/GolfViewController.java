package inw.golf.ui;

import inw.golf.board.Dimension;
import inw.golf.board.GameBoard;
import inw.golf.board.GameRound;
import inw.golf.gamerules.GameRuleFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GolfViewController {

    private GolfViewModel model;
    @FXML
    Label heading;
    @FXML
    Canvas mainCanvas;
    @FXML
    Button runBtn;
    @FXML
    Label roundCounter;
    @FXML TextField ySize;
    @FXML TextField xSize;
    @FXML TextField maxRound;
    private Timer gameTimer;

    public void Initialize(GolfViewModel mainModel) {
        model = mainModel;
        Stage stage = model.getStage();
        stage.setTitle("Yet another java game of life");
        SetupModel();
    }

    private void SetupModel() {
        model.board = new GameBoard(1, 1, 1);
        model.round = new GameRound(new GameRuleFactory().CreateDefaultRuleSet());
    }

    public void handleRunButtonAction(ActionEvent actionEvent) {
        playGame(model.round);
        runBtn.setDisable(true);
        heading.setText("Playing game of life for " + model.maxRounds + " rounds");
        drawGame();
    }

    private void playGame(GameRound round) {
        model.boardDimensions = ParseDimensions();
        model.maxRounds = ParseMaxRounds();
        model.board = new GameBoard(model.boardDimensions, 0.2);
        model.boards = new ArrayList<>();
        model.boards.add(model.board);
        for (int i = 0; i < model.maxRounds; i++)
            model.boards.add(round.Play(model.boards.get(i)));
    }

    private int ParseMaxRounds() {
        try {
            int maxRounds = Integer.parseInt(maxRound.getText());
            return maxRounds;
        }
        catch (NumberFormatException ex)
        {
            return 50;
        }
    }

    private Dimension ParseDimensions() {
        try {
            int x = Integer.parseInt(xSize.getText());
            int y = Integer.parseInt(ySize.getText());
            return new Dimension(x,y);
        }
        catch (NumberFormatException ex)
        {
            return new Dimension(400,200);
        }

    }

    private void drawGame()
    {
        gameTimer = new Timer();
        double mainCanvasWidth = mainCanvas.getWidth();
        double mainCanvasHeight = mainCanvas.getHeight();
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gameTimer.scheduleAtFixedRate(createTimerTask(mainCanvasWidth, mainCanvasHeight, gc), 100,100);
    }

    private TimerTask createTimerTask(double mainCanvasWidth, double mainCanvasHeight, GraphicsContext gc) {
        return new TimerTask() {
            int rnd = 0;
            @Override
            public void run() {
                Platform.runLater(() -> {
                    gc.clearRect(0,0, mainCanvasWidth, mainCanvasHeight);
                    drawBoard(gc, model.boards.get(rnd), (int)(Math.min(mainCanvasHeight, mainCanvasHeight)));
                    roundCounter.setText("Round " + rnd);
                });
                rnd++;
                if(rnd >= model.maxRounds)
                    cancel();
            }

            @Override
            public boolean cancel() {
                runBtn.setDisable(false);
                gameTimer.cancel();
                return super.cancel();
            }
        };
    }

    private void drawBoard(GraphicsContext gc, GameBoard board, int canvasSize) {
        Dimension boardDim = board.dimension;
        boolean[][] currentState = board.getPlayBoard();
        int blockSize = canvasSize / boardDim.y;
        for (int row = 1; row <= boardDim.y; row++) {
            for (int col = 1; col <= boardDim.x; col++) {
                if (currentState[col - 1][row - 1])
                    gc.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
            }
        }
    }

    public void exit() {
        if(gameTimer != null)
            gameTimer.cancel();
    }

    public void handleReplayButtonAction(ActionEvent actionEvent) {
        heading.setText("Replaying game of life for " + model.maxRounds + " rounds");
        drawGame();
    }
}
