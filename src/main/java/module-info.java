module com.example.clientaccountingtrafficviolations {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires com.dlsc.formsfx;
    requires com.jfoenix;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
    opens com.example.client.response to com.google.gson;
    exports com.example.client.response;
    opens com.example.client.entity to com.google.gson;
    exports com.example.client.entity;
    exports com.example.client.controller;
    opens com.example.client.controller to javafx.fxml;
}