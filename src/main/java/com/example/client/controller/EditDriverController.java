package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

import static com.example.client.controller.ApplicationController.*;
import static com.example.client.controller.ApplicationController.gson;

public class EditDriverController {
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

        try {
            field_fullName.setText(driver.getFullName());
            field_passportSeries.setText(driver.getPassportSeries());
            field_passportNumber.setText(driver.getPassportNumber());
            field_birthday.setText(driver.getBirthday());
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }

    @FXML
    void handleCancel(ActionEvent event) {
        driverStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        driver.setFullName(field_fullName.getText());
        driver.setPassportSeries(field_passportSeries.getText());
        driver.setPassportNumber(field_passportNumber.getText());
        driver.setBirthday(field_birthday.getText());

        okClicked = true;
        driverStage.close();
        driversData.set(driverId, driver);
        updateDriver(driver);
    }

    public static void updateDriver(DriverEntity driver) throws IOException {
        System.out.println(gson.toJson(driver));
        System.out.println(http.post("http://localhost:2825/api/v1/driver/update", gson.toJson(driver).toString()));
    }
}
