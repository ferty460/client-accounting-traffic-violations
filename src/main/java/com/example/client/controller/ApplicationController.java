package com.example.client.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ApplicationController {
    @FXML
    private JFXButton btn_auto, btn_drivers, btn_penalties, btn_violations;

    @FXML
    private AnchorPane pnl_cars, pnl_drivers, pnl_penalties, pnl_violations;

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
    private void initialize() throws Exception {

    }
}