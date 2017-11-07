package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.communication.UART;

public class Tab1Controller {

    private MainController controller;

    @FXML
    private void handleButton1(ActionEvent event) {
        System.out.println("click button 1");
        controller.scanDevices(new UART());
    }



    public void setController(MainController controller) {
        this.controller = controller;
    }
}
