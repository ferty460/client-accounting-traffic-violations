package com.example.client.controller;

import com.example.client.Application;
import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import com.example.client.entity.ViolationEntity;
import com.example.client.utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ApplicationController {
    public static String api = "http://localhost:2825/api/v1/";
    public static ObservableList<DriverEntity> driversData = FXCollections.observableArrayList();
    public static ObservableList<CarEntity> carsData = FXCollections.observableArrayList();
    public static ObservableList<PenaltyEntity> penaltiesData = FXCollections.observableArrayList();
    public static ObservableList<ViolationEntity> violationsData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    @FXML
    private Button btn_auto, btn_drivers, btn_penalties, btn_violations;

    @FXML
    private AnchorPane pnl_cars, pnl_drivers, pnl_penalties, pnl_violations;

    @FXML
    private TableView<ViolationEntity> table_violations;

    @FXML
    private TableView<DriverEntity> table_drivers;

    @FXML
    private TableView<CarEntity> table_cars;

    @FXML
    private TableView<PenaltyEntity> table_penalties;

    @FXML
    private TableColumn<ViolationEntity, String> violationId, violationAuto, violationDriver, violationPaid, violationPenalty, violationTime;

    @FXML
    private TableColumn<DriverEntity, String> driverId, driverFullName, driverPassportNumber, driverPassportSeries, driverBirthday;

    @FXML
    private TableColumn<CarEntity, String> carId, carBrand, carDriver, carNumber;

    @FXML
    private TableColumn<PenaltyEntity, String> penaltyId, penaltyDescription, penaltyPenalty, penaltyViolation;

    @FXML
    private void initialize() throws Exception {
        getDataViolations();
        getDataDrivers();
        getDataCars();
        getDataPenalties();

        updateViolationTable();
        updateDriverTable();
        updateCarTable();
        updatePenaltyTable();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_violations) {
            pnl_violations.toFront();
        } else if (event.getSource() == btn_drivers) {
            pnl_drivers.toFront();
        } else if (event.getSource() == btn_auto) {
            pnl_cars.toFront();
        } else if (event.getSource() == btn_penalties) {
            pnl_penalties.toFront();
        }
    }

    @FXML
    void addDriver(ActionEvent event) {
        DriverEntity driver = new DriverEntity();
        driversData.add(driver);
        Application.showDriverDialog(driver, driversData.size() - 1);
    }

    @FXML
    void deleteDriver(ActionEvent event) {

    }

    @FXML
    void editDriver(ActionEvent event) {

    }

    @FXML
    void sortDriver(ActionEvent event) {

    }


    @FXML
    void addViolation(ActionEvent event) {

    }

    @FXML
    void deleteViolation(ActionEvent event) {

    }

    @FXML
    void editViolation(ActionEvent event) {

    }

    @FXML
    void sortViolation(ActionEvent event) {

    }

    @FXML
    void addCar(ActionEvent event) throws IOException {
        CarEntity car = new CarEntity();
        carsData.add(car);
        Application.showCarDialog(car, carsData.size() - 1);
    }

    @FXML
    void deleteCar(ActionEvent event) {

    }

    @FXML
    void editCar(ActionEvent event) {

    }

    @FXML
    void sortCar(ActionEvent event) {

    }


    @FXML
    void addPenalty(ActionEvent event) {
        PenaltyEntity penalty = new PenaltyEntity();
        penaltiesData.add(penalty);
        Application.showPenaltyDialog(penalty, penaltiesData.size() - 1);
    }

    @FXML
    void deletePenalty(ActionEvent event) {

    }

    @FXML
    void editPenalty(ActionEvent event) {

    }

    @FXML
    void sortPenalty(ActionEvent event) {

    }

    private void updateViolationTable() {
        violationAuto.setCellValueFactory(new PropertyValueFactory<ViolationEntity, String>("car"));
        violationDriver.setCellValueFactory(new PropertyValueFactory<ViolationEntity, String>("driver"));
        violationPenalty.setCellValueFactory(new PropertyValueFactory<ViolationEntity, String>("penalty"));
        violationTime.setCellValueFactory(new PropertyValueFactory<ViolationEntity, String>("time"));
        violationPaid.setCellValueFactory(new PropertyValueFactory<ViolationEntity, String>("paid"));
        table_violations.setItems(violationsData);
    }

    private void updateDriverTable() {
        driverFullName.setCellValueFactory(new PropertyValueFactory<DriverEntity, String>("fullName"));
        driverPassportSeries.setCellValueFactory(new PropertyValueFactory<DriverEntity, String>("passportSeries"));
        driverPassportNumber.setCellValueFactory(new PropertyValueFactory<DriverEntity, String>("passportNumber"));
        driverBirthday.setCellValueFactory(new PropertyValueFactory<DriverEntity, String>("birthday"));
        table_drivers.setItems(driversData);
    }

    private void updateCarTable() {
        carBrand.setCellValueFactory(new PropertyValueFactory<CarEntity, String>("brand"));
        carNumber.setCellValueFactory(new PropertyValueFactory<CarEntity, String>("number"));
        carDriver.setCellValueFactory(new PropertyValueFactory<CarEntity, String>("driver"));
        table_cars.setItems(carsData);
    }

    private void updatePenaltyTable() {
        penaltyViolation.setCellValueFactory(new PropertyValueFactory<PenaltyEntity, String>("kind"));
        penaltyDescription.setCellValueFactory(new PropertyValueFactory<PenaltyEntity, String>("description"));
        penaltyPenalty.setCellValueFactory(new PropertyValueFactory<PenaltyEntity, String>("penalty"));
        table_penalties.setItems(penaltiesData);
    }

    public static void getDataViolations() throws Exception {
        String res = http.get(api, "violation/all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            ViolationEntity newVio = gson.fromJson(dataArr.get(i).toString(), ViolationEntity.class);
            violationsData.add(newVio);
        }
    }

    public static void getDataDrivers() throws Exception {
        String res = http.get(api, "driver/all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            DriverEntity newDriver = gson.fromJson(dataArr.get(i).toString(), DriverEntity.class);
            driversData.add(newDriver);
        }
    }

    public static void getDataCars() throws Exception {
        String res = http.get(api, "car/all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            CarEntity newCar = gson.fromJson(dataArr.get(i).toString(), CarEntity.class);
            carsData.add(newCar);
        }
    }

    public static void getDataPenalties() throws Exception {
        String res = http.get(api, "penalty/all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            PenaltyEntity newPenalty = gson.fromJson(dataArr.get(i).toString(), PenaltyEntity.class);
            penaltiesData.add(newPenalty);
        }
    }
}