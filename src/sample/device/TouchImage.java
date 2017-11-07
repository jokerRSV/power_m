package sample.device;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class TouchImage extends ImageView {
    private static long count = 1;
    private final long idTouchImage = count++;
    private boolean chargeState;
    private boolean powerSupplyState1;
    private boolean powerSupplyState2;

    public TouchImage(FXMLLoader loader) {
        super(new Image("/images/download.png"));
        setFitWidth(30);
        setFitHeight(32);

        Stage secondaryStage = new Stage();
//        secondaryStage.initStyle(StageStyle.UNIFIED);
        secondaryStage.initStyle(StageStyle.UTILITY);
        secondaryStage.setTitle("device " + String.valueOf(this.idTouchImage));
        secondaryStage.setResizable(false);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        DeviceController deviceController = loader.getController();
        deviceController.setTouchImage(this);
        root.setOnMouseClicked(event2 -> secondaryStage.close());
        Scene scene = new Scene(root, 299, 233);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setScene(scene);

        setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) this.getScene().getWindow();
            double startPos = primaryStage.getY();
            double endPos = primaryStage.getY() + 150;
            DoubleProperty y = new SimpleDoubleProperty(startPos);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2),
                    new KeyValue(y, endPos, Interpolator.EASE_BOTH)));
            y.addListener((obs, oldValue, newValue) -> secondaryStage.setY(newValue.doubleValue()));
            timeline.play();

            secondaryStage.showAndWait();
        });
    }

    public static void addDevice(GridPane gridPane) {
        FXMLLoader loader = new FXMLLoader(TouchImage.class.getResource("device.fxml"));
        ImageView imageView = new TouchImage(loader);
        TouchImage touchImage = (TouchImage) imageView;
        DropShadow shadow = new DropShadow(8, 6, 6, Color.DARKSLATEGRAY);
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event3 -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> imageView.setEffect(null));
        gridPane.add(imageView, (int) touchImage.getIdTouchImage() - 1, 0);
        gridPane.setMargin(imageView, new Insets(0, 0, 0, 25));
    }

    public long getIdTouchImage() {
        return idTouchImage;
    }

    public boolean isChargeState() {
        return chargeState;
    }

    public void setChargeState(boolean chargeState) {
        this.chargeState = chargeState;
    }

    public boolean isPowerSupplyState1() {
        return powerSupplyState1;
    }

    public void setPowerSupplyState1(boolean powerSupplyState1) {
        this.powerSupplyState1 = powerSupplyState1;
    }

    public boolean isPowerSupplyState2() {
        return powerSupplyState2;
    }

    public void setPowerSupplyState2(boolean powerSupplyState2) {
        this.powerSupplyState2 = powerSupplyState2;
    }
}
