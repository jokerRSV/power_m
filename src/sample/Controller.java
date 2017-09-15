package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
        imageView.setOnMouseClicked(event1 -> {
            System.out.println("show new image " + imageView.getId());
        });
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event3 -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> imageView.setEffect(null));
        imageHBox.getChildren().add(imageView);
        System.out.println("click button charge");

    }
}
