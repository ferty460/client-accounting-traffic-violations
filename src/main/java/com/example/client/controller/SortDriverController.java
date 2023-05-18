package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import com.example.client.entity.ViolationEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class SortDriverController {
    @FXML
    private ComboBox<ViolationEntity> listViolations;

    @FXML
    private TextField date, numCar, value, numberPassport, seriesPassport;

    private Stage driverStage;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.driverStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void initialize() {
        listViolations.setItems(FXCollections.observableArrayList(violationsData));
        listViolations.setValue(violationsData.get(0));
    }

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        getAll();
    }

    @FXML
    void getAll() throws IOException {
        try {
            String res = http.get(api, "driver/all");
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                driversData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void greaterThanOne(ActionEvent event) throws IOException {
        try {
            String res = http.get(api, "driver/violations_greater1");
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                driversData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void isHaveViolation(ActionEvent event) throws IOException {
        try {
            String res = http.get(api, "driver/violations?kind=" + listViolations.getValue());
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                driversData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void notPaid(ActionEvent event) throws IOException {
        try {
            String res = http.get(api, "driver/paid_equal0");
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                driversData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void numAuto(ActionEvent event) throws IOException {
        if (numCar.getText() == null || !numCar.getText().matches("[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(driverStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText("Поле \"Номер автомобиля\" не соответствует формату (А111АА)");
            alert.showAndWait();
        } else {
            try {
                String res = http.get(api, "driver/car?number=" + numCar.getText());
                System.out.println(res);
                JsonObject base = gson.fromJson(res, JsonObject.class);
                JsonObject dataArr = base.getAsJsonObject("data");
                DriverEntity newDriver = gson.fromJson(dataArr.toString(), DriverEntity.class);
                driversData.add(newDriver);
            } catch (NullPointerException e) {
                e.getMessage();
            }
            driverStage.close();
        }
    }

    @FXML
    void paid(ActionEvent event) throws IOException {
        try {
            String res = http.get(api, "driver/paid_lessPenalty");
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                driversData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void passportData(ActionEvent event) throws IOException {
        if (seriesPassport.getText() == null || numberPassport.getText() == null ||
                !seriesPassport.getText().matches("[0-9]{4}") || !numberPassport.getText().matches("[0-9]{6}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(driverStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText("Поле \"Серия паспорта\" (1111) или \"Номер паспорта\" (111111) неверно заполнено");
            alert.showAndWait();
        } else {
            try {
                String res = http.get(api, "driver/passport?series=" + seriesPassport.getText() + "&number=" + numberPassport.getText());
                System.out.println(res);
                JsonObject base = gson.fromJson(res, JsonObject.class);
                JsonObject dataArr = base.getAsJsonObject("data");
                DriverEntity newDriver = gson.fromJson(dataArr.toString(), DriverEntity.class);
                driversData.add(newDriver);
            } catch (NullPointerException e) {
                e.getMessage();
            }
            driverStage.close();
        }
    }

    @FXML
    void sumGreaterThan(ActionEvent event) throws IOException {
        if (value.getText() == null || !value.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(driverStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText("Поле неверно заполнено");
            alert.showAndWait();
        } else {
            try {
                String res = http.get(api, "driver/penalties_sum?n=" + value.getText());
                System.out.println(res);
                JsonObject base = gson.fromJson(res, JsonObject.class);
                JsonArray dataArr = base.getAsJsonArray("data");
                for (int i = 0; i < dataArr.size(); i++) {
                    DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                    driversData.add(newDriver);
                }
            } catch (NullPointerException e) {
                e.getMessage();
            }
            driverStage.close();
        }
    }

    @FXML
    void violationInDate(ActionEvent event) throws IOException {
        if (date.getText() == null || !date.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(driverStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText("Поле неверно заполнено");
            alert.showAndWait();
        } else {
            try {
                String res = http.get(api, "driver/violation?time=" + date.getText());
                System.out.println(res);
                JsonObject base = gson.fromJson(res, JsonObject.class);
                JsonArray dataArr = base.getAsJsonArray("data");
                for (int i = 0; i < dataArr.size(); i++) {
                    DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
                    driversData.add(newDriver);
                }
            } catch (NullPointerException e) {
                e.getMessage();
            }
            driverStage.close();
        }
    }
}
