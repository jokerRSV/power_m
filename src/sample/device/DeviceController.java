package sample.device;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class DeviceController {
    @FXML
    private CheckBox chargeDev;
    @FXML
    private CheckBox powerSuppl1;
    @FXML
    private CheckBox powerSuppl2;
    private TouchImage touchImage;

    public void setTouchImage(TouchImage touchImage) {
//        chargeDev.setSelected(touchImage.isChargeState());
//        powerSuppl1.setSelected(touchImage.isPowerSupplyState1());
//        powerSuppl2.setSelected(touchImage.isPowerSupplyState2());
        this.touchImage = touchImage;
    }

    @FXML
    public void initialize() {
        chargeDev.selectedProperty().addListener((observable, oldValue, newValue) -> touchImage.setChargeState(newValue));
        powerSuppl1.selectedProperty().addListener((observable, oldValue, newValue) -> touchImage.setPowerSupplyState1(newValue));
        powerSuppl2.selectedProperty().addListener((observable, oldValue, newValue) -> touchImage.setPowerSupplyState2(newValue));
//        chargeDev.setOnAction(event -> touchImage.setChargeState(chargeDev.isSelected()));
//        powerSuppl1.setOnAction(event -> touchImage.setPowerSupplyState1(powerSuppl1.isSelected()));
//        powerSuppl2.setOnAction(event -> touchImage.setPowerSupplyState2(powerSuppl2.isSelected()));
    }

}
