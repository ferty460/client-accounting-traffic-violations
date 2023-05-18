package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import com.example.client.entity.ViolationEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;
import static com.example.client.controller.ApplicationController.gson;

public class SortViolationController {

    @FXML
    private ComboBox<DriverEntity> driversList;

    private Stage driverStage;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.driverStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void initialize() {
        driversList.setItems(FXCollections.observableArrayList(driversData));
        driversList.setValue(driversData.get(0));
    }

    @FXML
    void getAll() throws IOException {
        try {
            String res = http.get(api, "violation/all");
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                ViolationEntity newDriver = gson.fromJson(dataArr.get(i).toString(), ViolationEntity.class);
                violationsData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        getAll();
    }

    @FXML
    void find(ActionEvent event) throws IOException {
        try {
            String res = http.get(api, "violation/all_byDriver?id=" + driversList.getValue().getDriver_Id());
            System.out.println(res);
            JsonObject base = gson.fromJson(res, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (int i = 0; i < dataArr.size(); i++) {
                ViolationEntity newDriver = gson.fromJson(dataArr.get(i).toString(), ViolationEntity.class);
                violationsData.add(newDriver);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        driverStage.close();
    }
}
