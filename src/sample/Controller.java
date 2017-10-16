package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Locale;

public class Controller {
    private static int id;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private RadioMenuItem engItemMenu;
    @FXML
    private RadioMenuItem rusItemMenu;
    @FXML
    private Menu menuHelp;
    @FXML
    private Menu menuOptions;
    @FXML
    private Menu menuLang;
    @FXML
    private HBox imageHBox;
    @FXML
    private Button chargeButton;
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {
        tab1Controller.setController(this);

        menuOptions.textProperty().bind(I18N.createStringBinding("menu.option"));
        menuLang.textProperty().bind(I18N.createStringBinding("menu.lang"));
        engItemMenu.textProperty().bind(I18N.createStringBinding("menu.item.eng"));
        rusItemMenu.textProperty().bind(I18N.createStringBinding("menu.item.rus"));

        menuHelp.textProperty().bind(I18N.createStringBinding("menu.help"));
        menuItemAbout.textProperty().bind(I18N.createStringBinding("menu.item.about"));

        engItemMenu.setOnAction(event -> I18N.setLocale(I18N.getSupportedLocales().get(0)));
        rusItemMenu.setOnAction(event -> I18N.setLocale(I18N.getSupportedLocales().get(1)));

        if (Locale.getDefault().toString().equals("ru_RU")) rusItemMenu.setSelected(true);
        else engItemMenu.setSelected(true);

        DropShadow shadow = new DropShadow();

        chargeButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> chargeButton.setEffect(shadow));
        chargeButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> chargeButton.setEffect(null));
    }

    @FXML
    private void handleButtonCharge(ActionEvent event) {
        DropShadow shadow = new DropShadow();

        ImageView imageView = new ImageView("/images/untitled-1.gif");
        imageView.setId(String.valueOf(id++));
        imageView.setFitWidth(30);
        imageView.setFitHeight(32);

//        Stage primaryStage = (Stage) borderPane.getScene().getWindow();

        imageView.setOnMouseClicked(event1 -> {
            Stage secondaryStage = new Stage();
            ImageView image= (ImageView) event1.getSource();
            StackPane content = new StackPane(new Label("Preferences "+ image.getId()));
            content.setStyle("-fx-background-color: lightgray; -fx-padding: 40;");
            content.setOnMouseClicked(event2 -> secondaryStage.close());

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double startPos = primaryScreenBounds.getMaxY();
            double endPos = primaryScreenBounds.getMaxY() / 3;
            DoubleProperty y = new SimpleDoubleProperty(startPos);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5),
                    new KeyValue(y, endPos, Interpolator.EASE_BOTH)));
            y.addListener((obs, oldValue, newValue) -> secondaryStage.setY(newValue.doubleValue()));
            timeline.play();
//            window.setX(primaryScreenBounds.getMaxX() - 120);
//            window.show(primaryStage);
            Scene scene = new Scene(content, 555, 355);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.setScene(scene);
            secondaryStage.showAndWait();

        });
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event3 -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> imageView.setEffect(null));
        imageHBox.getChildren().add(imageView);
        System.out.println("click button charge");

    }
}
