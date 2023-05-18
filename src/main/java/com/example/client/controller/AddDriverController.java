package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if (isInputValid()) {
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
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (field_fullName.getText() == null || field_fullName.getText().length() == 0
                || !(field_fullName.getText().matches("[А-Я][а-я]{3,15}\s[А-Я][а-я]{3,15}\s[А-Я][а-я]{3,19}")
                || !(field_fullName.getText().matches("[А-Я][а-я]{3,15}\s[А-Я].\s[А-Я].")))) {
            errorMessage = "Поле не соответствует формату \n(Иванов Иван Иванович)";
        }
        if (field_passportNumber.getText() == null || field_passportNumber.getText().length() == 0
                || !(field_passportNumber.getText().matches("[0-9]{6}"))) {
            errorMessage = "Поле \"Номер паспорта\" не соответствует формату(123456)";
        }
        if (field_passportSeries.getText() == null || field_passportSeries.getText().length() == 0
                || !(field_passportSeries.getText().matches("[0-9]{4}"))) {
            errorMessage = "Поле \"Серия паспорта\" не соответствует формату (1234)";
        }
        if (field_birthday.getText() == null || field_birthday.getText().length() == 0
                || !(field_birthday.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))) {
            errorMessage = "Поле \"Дата рождения\" не соответствует формату (2023-05-05)";
        }

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(driverStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void addDriver(DriverEntity driver) throws IOException {
        driver.setDriver_Id(null);
        System.out.println(gson.toJson(driver));
        System.out.println(http.post("http://localhost:2825/api/v1/driver/add", gson.toJson(driver)));
    }
}
