package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

import static com.example.client.controller.ApplicationController.*;
import static com.example.client.controller.ApplicationController.gson;

public class AddDriverController {

    @FXML
    private TextField field_fullName, field_passportNumber, field_passportSeries, field_birthday;

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

    public void setLabels(DriverEntity driverIn, int driverId) {
        this.driver = driverIn;
        this.driverId = driverId;

        field_fullName.setText(driver.getFullName());
        field_passportSeries.setText(driver.getPassportSeries());
        field_passportNumber.setText(driver.getPassportNumber());
        field_birthday.setText(driver.getBirthday());
    }

    @FXML
    void handleCancel(ActionEvent event) {
        driverStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws ParseException, IOException {
        driver.setDriver_Id((long) driverId);
        driver.setFullName(field_fullName.getText());
        driver.setPassportSeries(field_passportSeries.getText());
        driver.setPassportNumber(field_passportNumber.getText());
        driver.setBirthday(field_birthday.getText());

        okClicked = true;
        driverStage.close();
        driversData.set(driverId, driver);
        addDriver(driver);
    }

    public static void addDriver(DriverEntity driver) throws IOException {
        driver.setDriver_Id(null);
        System.out.println(gson.toJson(driver));
        System.out.println(http.post("http://localhost:2825/api/v1/driver/add", gson.toJson(driver)));
        http.post("http://localhost:2825/api/v1/driver/add", gson.toJson(driver));
    }
}
