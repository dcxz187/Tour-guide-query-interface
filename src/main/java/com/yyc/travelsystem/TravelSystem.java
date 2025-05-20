package com.yyc.travelsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TravelSystem extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/yyc/travelsystem/login.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("导游查询系统 - 登录");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
