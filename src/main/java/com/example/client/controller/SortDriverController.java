package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import com.example.client.entity.ViolationEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class SortDriverController {
    @FXML
    private TextField date;

    @FXML
    private ComboBox<ViolationEntity> listViolations;

    @FXML
    private TextField numCar;

    @FXML
    private TextField value;

    private Stage driverStage;
    private DriverEntity driver;
    private int driverId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.driverStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
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
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void isHaveViolation(ActionEvent event) {

    }

    @FXML
    void notPaid(ActionEvent event) {

    }

    @FXML
    void numAuto(ActionEvent event) {

    }

    @FXML
    void paid(ActionEvent event) {

    }

    @FXML
    void passportData(ActionEvent event) {

    }

    @FXML
    void sumGreaterThan(ActionEvent event) {

    }

    @FXML
    void violationInDate(ActionEvent event) {

    }

}
