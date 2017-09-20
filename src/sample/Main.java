package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.titleProperty().bind(I18N.createStringBinding("title.stage", 1, 0));

        ContextMenu contextMenu = new ContextMenu(new MenuItem("Item1"), new MenuItem("Item2"));
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 240);
        scene.setOnContextMenuRequested(event1 -> {
            System.out.println("pressed on scene");
            contextMenu.show(root, event1.getScreenX(), event1.getScreenY());
            double start = scene.getWindow().getY();
            double end = scene.getWindow().getX();
            contextMenu.setY(start);
            final DoubleProperty property = new SimpleDoubleProperty(start);
            property.addListener((observable, oldValue, newValue) -> contextMenu.setY(newValue.doubleValue()));

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(property, end, Interpolator.EASE_BOTH))
            );
            timeline.play();
        });

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
