package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sample.communication.UART;
import sample.device.DeviceController;
import sample.device.TouchImage;
import sample.utils.I18N;

import java.util.Locale;

public class MainController {
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
    private DeviceController deviceController;
//        Stage primaryStage = (Stage) borderPane.getScene().getWindow();

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

        for (int i = 0; i < UART.getDeviceNumber(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("device/device.fxml"));
            ImageView imageView = new TouchImage(loader);
            DropShadow shadow = new DropShadow(8, 6, 6, Color.DARKSLATEGRAY);
            chargeButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event1 -> chargeButton.setEffect(new Lighting()));
            chargeButton.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> chargeButton.setEffect(null));
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event3 -> imageView.setEffect(shadow));
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> imageView.setEffect(null));
            imageHBox.getChildren().add(imageView);
        }
    }
}
