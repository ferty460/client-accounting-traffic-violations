package com.example.client.controller;

import com.example.client.entity.PenaltyEntity;
import com.example.client.entity.ViolationEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

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

        try {
            lbl_car.setText(violation.getCar().getBrand());
            lbl_driver.setText(violation.getDriver().getFullName());
            lbl_penalty.setText(violation.getPaid());
            lbl_time.setText(violation.getTime());
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }

    @FXML
    void handleCancel(ActionEvent event) {
        payViolationStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        if (isInputValid()) {
            violation.setPaid(field_violationPay.getText());

            okClicked = true;
            payViolationStage.close();
            violationsData.set(violationId, violation);
            payViolation(violation);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (!field_violationPay.getText().matches("[0-9]+")) {
            errorMessage = "Только целочисленный формат!";
        } else if (Integer.parseInt(field_violationPay.getText()) > violation.getPenalty().getPenalty()) {
            errorMessage = "Переплата";
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

    public static void payViolation(ViolationEntity violation) throws IOException {
        System.out.println(gson.toJson(violation));
        System.out.println(http.post("http://localhost:2825/api/v1/violation/update", gson.toJson(violation)));
    }
}
