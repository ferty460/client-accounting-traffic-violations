package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if (isInputValid()) {
            penalty.setPenalty_Id((long) penaltyId);
            penalty.setKind(field_penaltyKind.getText());
            penalty.setDescription(field_penaltyDesc.getText());
            penalty.setPenalty(Integer.parseInt(field_penaltySum.getText()));

            okClicked = true;
            penaltyStage.close();
            penaltiesData.set(penaltyId, penalty);
            addPenalty(penalty);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (field_penaltyKind.getText() == null || field_penaltyKind.getText().length() == 0) {
            errorMessage = "Поле \"Вид нарушения\" не может быть пустым";
        }
        if (field_penaltyDesc.getText() == null || field_penaltyDesc.getText().length() == 0) {
            errorMessage = "Поле \"Описание нарушения\" не может быть пустым";
        }
        if (field_penaltySum.getText() == null || field_penaltySum.getText().length() == 0
                || Integer.parseInt(field_penaltySum.getText()) < 500 || Integer.parseInt(field_penaltySum.getText()) > 300000) {
            errorMessage = "Поле \"Сумма штрафа\" не может быть меньше 500 и больше 300 000";
        }

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(penaltyStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void addPenalty(PenaltyEntity penalty) throws IOException {
        penalty.setPenalty_Id(null);
        System.out.println(gson.toJson(penalty));
        System.out.println(http.post("http://localhost:2825/api/v1/penalty/add", gson.toJson(penalty)));
    }
}
