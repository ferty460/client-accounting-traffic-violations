package com.example.client;

import com.example.client.controller.AddCarController;
import com.example.client.controller.AddDriverController;
import com.example.client.controller.AddPenaltyController;
import com.example.client.entity.CarEntity;
import com.example.client.entity.DriverEntity;
import com.example.client.entity.PenaltyEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 640);
        stage.setTitle("Учет нарушений ПДД");
        stage.setScene(scene);
        stage.show();
    }

    public static boolean showDriverDialog(DriverEntity driver, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/addDriver.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Водитель");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddDriverController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(driver, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showCarDialog(CarEntity car, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/addCar.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Автомобиль");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddCarController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(car, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showPenaltyDialog(PenaltyEntity penalty, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/addPenalty.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Штраф");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddPenaltyController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(penalty, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}