package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Tab1Controller {

    private Controller controller;

    @FXML
    private void handleButton1(ActionEvent event) {
        System.out.println("click button 1");
        System.out.println(Exam.Login.getTitle());
//        controller.getPowerBox1().fire();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
