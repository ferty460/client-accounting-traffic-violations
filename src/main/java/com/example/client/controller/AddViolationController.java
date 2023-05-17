package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import com.example.client.entity.ViolationEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class AddViolationController {

    @FXML
    private ComboBox<CarEntity> carList;

    @FXML
    private TextField field_violationPaid, field_violationTime;

    @FXML
    private ComboBox<PenaltyEntity> penaltyList;

    @FXML
    private ComboBox<DriverEntity> driverList;

    private Stage violationStage;
    private ViolationEntity violation;
    private int violationId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.violationStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(ViolationEntity violationIn, int violationId) {
        this.violation = violationIn;
        this.violationId = violationId;

        field_violationPaid.setText(violation.getPaid());
        field_violationTime.setText(violation.getTime());
    }

    public void initialize() {
        carList.setItems(FXCollections.observableArrayList(carsData));
        carList.setValue(carsData.get(0));
        penaltyList.setItems(FXCollections.observableArrayList(penaltiesData));
        penaltyList.setValue(penaltiesData.get(0));
        driverList.setItems(FXCollections.observableArrayList(driversData));
        driverList.setValue(driversData.get(0));
    }

    @FXML
    void handleCancel(ActionEvent event) {
        violationStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        if (isInputValid()) {
            violation.setViolation_Id((long) violationId);
            violation.setPaid(field_violationPaid.getText());
            violation.setTime(field_violationTime.getText());
            violation.setCar(carList.getValue());
            violation.setPenalty(penaltyList.getValue());
            violation.setDriver(driverList.getValue());

            okClicked = true;
            violationStage.close();
            violationsData.set(violationId, violation);
            addViolation(violation);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (field_violationTime.getText() == null || field_violationTime.getText().length() == 0
                || !(field_violationTime.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))) {
            errorMessage = "Поле \"Дата нарушения\" не соответствует формату \n (2020-12-20)";
        }
        if (field_violationPaid.getText() == null || field_violationPaid.getText().length() == 0
                || Integer.parseInt(field_violationPaid.getText()) < 0
                || Integer.parseInt(field_violationPaid.getText()) > Integer.parseInt(String.valueOf(penaltyList.getValue().getPenalty()))) {
            errorMessage = "Поле \"Оплачено\" не может быть меньше 0 и больше суммы штрафа";
        }
        if (!driverList.getValue().getDriver_Id().equals(carList.getValue().getDriver().getDriver_Id())) {
            errorMessage = "Данный автомобиль не принадлежит выбранному водителю";
        }

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(violationStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void addViolation(ViolationEntity violation) throws IOException {
        violation.setViolation_Id(null);
        System.out.println(gson.toJson(violation));
        System.out.println(http.post("http://localhost:2825/api/v1/violation/add", gson.toJson(violation)));
    }
}
