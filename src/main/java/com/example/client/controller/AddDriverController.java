package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.client.controller.ApplicationController.driversData;

public class AddDriverController {

    @FXML
    private TextField field_birthday, field_fullName, field_passportNumber, field_passportSeries;

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
        field_birthday.setText(String.valueOf(driver.getBirthday()));
    }

    @FXML
    void handleCancel(ActionEvent event) {
        driverStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) {
        driver.setDriver_Id((long) driverId);
        driver.setFullName(field_fullName.getText());
        driver.setPassportSeries(field_passportSeries.getText());
        driver.setPassportNumber(field_passportNumber.getText());
        /*driver.setBirthday(field_birthday.);*/

        okClicked = true;
        driverStage.close();
        driversData.set(driverId, driver);
    }
}
