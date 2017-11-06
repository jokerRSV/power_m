package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.close.CloseBox;
import sample.utils.I18N;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.titleProperty().bind(I18N.createStringBinding("title.stage", 1, 0));
        primaryStage.setScene(new Scene(root, 383, 350));
        primaryStage.setX(970);
        primaryStage.setY(100);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            boolean value = CloseBox.display(primaryStage);
            if (!value) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

