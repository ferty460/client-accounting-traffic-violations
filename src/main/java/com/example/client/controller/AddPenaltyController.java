package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class AddPenaltyController {

    @FXML
    private TextField field_penaltyDesc, field_penaltyKind, field_penaltySum;

    private Stage penaltyStage;
    private PenaltyEntity penalty;
    private int penaltyId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.penaltyStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(PenaltyEntity penaltyIn, int penaltyId) {
        this.penalty = penaltyIn;
        this.penaltyId = penaltyId;

        field_penaltyKind.setText(penalty.getKind());
        field_penaltyDesc.setText(penalty.getDescription());
        field_penaltySum.setText(String.valueOf(penalty.getPenalty()));
    }

    @FXML
    void handleCancel(ActionEvent event) {
        penaltyStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        penalty.setPenalty_Id((long) penaltyId);
        penalty.setKind(field_penaltyKind.getText());
        penalty.setDescription(field_penaltyDesc.getText());
        penalty.setPenalty(Integer.parseInt(field_penaltySum.getText()));

        okClicked = true;
        penaltyStage.close();
        penaltiesData.set(penaltyId, penalty);
        addPenalty(penalty);
    }

    public static void addPenalty(PenaltyEntity penalty) throws IOException {
        penalty.setPenalty_Id(null);
        System.out.println(gson.toJson(penalty));
        System.out.println(http.post("http://localhost:2825/api/v1/penalty/add", gson.toJson(penalty)));
        http.post("http://localhost:2825/api/v1/penalty/add", gson.toJson(penalty));
    }
}
