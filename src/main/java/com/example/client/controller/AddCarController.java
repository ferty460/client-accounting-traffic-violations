package com.example.client.controller;

import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class AddCarController {
    @FXML
    private TextField field_carBrand, field_carNumber;

    @FXML
    private ComboBox<DriverEntity> driverList;

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

    public void initialize() {
        driverList.setItems(FXCollections.observableArrayList(driversData));
        driverList.setValue(driversData.get(0));
    }

    public void setLabels(CarEntity carIn, int carId) {
        this.car = carIn;
        this.carId = carId;

        field_carBrand.setText(car.getBrand());
        field_carNumber.setText(car.getNumber());
    }

    @FXML
    void handleCancel(ActionEvent event) {
        carStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
        car.setCar_Id((long) carId);
        car.setBrand(field_carBrand.getText());
        car.setNumber(field_carNumber.getText());
        car.setDriver(driverList.getValue());

        okClicked = true;
        carStage.close();
        carsData.set(carId, car);
        addCar(car);
    }

    public static void addCar(CarEntity car) throws IOException {
        car.setCar_Id(null);
        System.out.println(gson.toJson(car));
        System.out.println(http.post("http://localhost:2825/api/v1/car/add", gson.toJson(car)));
        http.post("http://localhost:2825/api/v1/car/add", gson.toJson(car));
    }
}
