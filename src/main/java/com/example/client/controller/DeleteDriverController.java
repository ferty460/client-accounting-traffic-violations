package com.example.client.controller;

import com.example.client.entity.DriverEntity;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.*;

public class DeleteDriverController {
    private Stage confirmDeleteStage;
    private int driverID;
    private DriverEntity driver;
    public void setDialogStage (Stage dialogStage) {this.confirmDeleteStage = dialogStage;}
    private boolean okClicked = false;
    public boolean isOkClicked() {return okClicked;}

    @FXML
    private void handleCancel() {confirmDeleteStage.close();}

    @FXML
    private void handleOk() throws IOException {
        okClicked = true;
        confirmDeleteStage.close();
        deleteDriver(driver);
    }

    public void setId(DriverEntity driverIn, int driverId) {
        this.driver = driverIn;
        this.driverID = driverId;
    }

    public static void deleteDriver (DriverEntity driver) throws IOException {
        try {
            System.out.println(driver.getDriver_Id());
            System.out.println(http.delete(api+"driver/", driver.getDriver_Id()));
            driversData.remove(driver);
        } catch (NullPointerException e) {
            e.getMessage();
        }

    }
}
