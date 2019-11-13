package inw.golf.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GolfUi extends Application {


    private GolfViewController mainController;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GolfView.fxml"));
        stage.setScene(new Scene(loader.load()));
        GolfViewModel mainModel = new GolfViewModel(stage);

        mainController = loader.<GolfViewController>getController();
        mainController.Initialize(mainModel);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        mainController.exit();
    }
}
