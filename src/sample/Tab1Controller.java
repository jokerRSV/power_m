package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Tab1Controller {

    private Controller controller;

    @FXML
    private void handleButton1(ActionEvent event) {
        System.out.println("click button 1");
//        System.out.println(Exam.Login.getTitle());
//        controller.getPowerBox1().fire();

       /* ContextMenu contextMenu = new ContextMenu(new MenuItem("Item1"), new MenuItem("Item2"));
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

        Stage primaryStage = new Stage();
        primaryStage.setTitle("popup");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();*/
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
