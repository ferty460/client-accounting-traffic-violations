package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import com.example.client.entity.ViolationEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PayViolationController {
    @FXML
    private TextField field_violationPay;

    @FXML
    private Label lbl_car, lbl_driver, lbl_penalty, lbl_time;

    private Stage payViolationStage;
    private ViolationEntity violation;
    private int violationId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.payViolationStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(ViolationEntity violationIn, int violationId) {
        this.violation = violationIn;
        this.violationId = violationId;

        lbl_car.setText(violation.getCar().getBrand());
        lbl_driver.setText(violation.getDriver().getFullName());
        lbl_penalty.setText(violation.getPaid());
        lbl_time.setText(violation.getTime());
    }

    @FXML
    void handleCancel(ActionEvent event) {
        payViolationStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) {
        violation.setPaid(violation.getPaid() + Integer.parseInt(field_violationPay.getText()));
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (!field_violationPay.getText().matches("[0-9]+")) {
            errorMessage = "Только целочисленный формат!";
        }
        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(payViolationStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
