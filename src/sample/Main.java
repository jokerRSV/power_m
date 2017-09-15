package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.titleProperty().bind(I18N.createStringBinding("title.stage", 1, 0));
        primaryStage.setScene(new Scene(root, 383, 350));
        primaryStage.setX(1450);
        primaryStage.setY(300);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            boolean value = CloseBox.display(I18N.get("title.exit"), I18N.get("text.exit"));
            if (!value) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
