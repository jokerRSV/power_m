package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CloseBox {
    private static boolean value;

    public static boolean display(String title, String message) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setMinWidth(150);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 0, 0, 0));
//        hBox.setStyle("-fx-background-color: #d2d2d2;");

        Text text = new Text(message);
        hBox.getChildren().addAll(text);

        GridPane gridPane = new GridPane();     //spacing - distance between label and button
        gridPane.setPadding(new Insets(5, 0, 0, 0));
        gridPane.setHgap(60);
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setGridLinesVisible(true);
//        gridPane.setStyle("-fx-background-color: #336699;");

        Button yesButton = new Button();
        yesButton.textProperty().bind(I18N.createStringBinding("exit"));
        yesButton.setPrefSize(55, 25);
        yesButton.setOnAction(event -> {
            value = true;
            primaryStage.close();
        });
        GridPane.setConstraints(yesButton, 0, 1);

        Button noButton = new Button();
        noButton.textProperty().bind(I18N.createStringBinding("cancel"));
        noButton.setPrefSize(65, 25);
        noButton.setOnAction(event -> {
            value = false;
            primaryStage.close();
        });
        GridPane.setConstraints(noButton, 1, 1);

        gridPane.getChildren().addAll(yesButton, noButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(gridPane);
        borderPane.setStyle("-fx-background-color: #d2d2d2;");

//        Scene scene = new Scene(borderPane, 295, 93);
        Scene scene = new Scene(borderPane, 255, 93);
        scene.getStylesheets().add("sample/css/GUI.css");


        DoubleProperty opacity = borderPane.opacityProperty();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0))
        );
        timeline.play();


        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(275);
        primaryStage.setMaxHeight(113);
        primaryStage.showAndWait();
        return value;
    }
}
