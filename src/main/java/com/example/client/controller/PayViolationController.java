package com.example.client.controller;

import com.example.client.entity.PenaltyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PayViolationController {
    @FXML
    private TextField field_violationPay;

    private Stage payViolationStage;
    private PenaltyEntity penalty;
    private int penaltyId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.payViolationStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleOk(ActionEvent event) {

    }
}
