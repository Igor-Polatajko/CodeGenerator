package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.CodeCalculator;

import java.util.Map;

public class MainSceneController {

    @FXML
    private TextField phaseField;

    @FXML
    private TextField keyField;

    @FXML
    private TextField codeField;

    private CodeCalculator calculator = new CodeCalculator();

    @FXML
    private void onCalculateButtonClick() {
        Map<String, Object> result = calculator.calculate(phaseField.getText(), keyField.getText());

        codeField.setText((String) result.get("Code"));

        if ((Boolean) result.get("IsError")) {
            codeField.setStyle("-fx-control-inner-background: #FF0000");
        }
        else {
            codeField.setStyle("-fx-control-inner-background: #FFFFFF");
        }
    }
}
