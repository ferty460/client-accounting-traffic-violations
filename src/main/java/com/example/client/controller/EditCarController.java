package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class EditCarController {

    @FXML
    private ComboBox<DriverEntity> driverList;

    @FXML
    private TextField field_carBrand;

    @FXML
    private TextField field_carNumber;

    private Stage carStage;
    private CarEntity car;
    private int carId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.carStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(CarEntity carIn, int carId) {
        this.car = carIn;
        this.carId = carId;

        try {
            field_carBrand.setText(car.getBrand());
            field_carNumber.setText(car.getNumber());
            driverList.setItems(FXCollections.observableArrayList(driversData));
            driverList.setValue(driversData.get(0));
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }

    @FXML
    void handleCancel(ActionEvent event) {
        carStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        if (isInputValid()) {
            car.setBrand(field_carBrand.getText());
            car.setNumber(field_carNumber.getText());
            car.setDriver(driverList.getValue());

            okClicked = true;
            carStage.close();
            carsData.set(carId, car);
            addCar(car);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (field_carNumber.getText() == null || field_carNumber.getText().length() == 0
                || !(field_carNumber.getText().matches("[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2}"))) {
            errorMessage = "Поле \"Номер автомобиля\" не соответствует формату (А111АА)";
        }
        if (field_carBrand.getText() == null || field_carBrand.getText().length() == 0) {
            errorMessage = "Поле \"Марка автомобиля\" не должно быть пустым";
        }

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(carStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void addCar(CarEntity car) throws IOException {
        System.out.println(gson.toJson(car));
        System.out.println(http.post("http://localhost:2825/api/v1/car/update", gson.toJson(car)));
    }
}
