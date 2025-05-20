package com.yyc.TourGuideQueryInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.logging.Logger;

public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    @FXML
    public Button loginButton;
    @FXML
    private TextField guideIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private final GuideModel model = new GuideModel();

    @FXML
    private void handleLogin() {
        String guideId = guideIdField.getText();
        String password = passwordField.getText();

        try {
            if (model.authenticate(guideId, password)) {
                // 加载主界面
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/yyc/TourGuideQueryInterface/main.fxml"));
                Parent root = loader.load();
                MainController mainController = loader.getController();
                mainController.setGuideId(guideId);

                Stage stage = (Stage) guideIdField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("导游查询系统 - 主界面");
            } else {
                logger.warning("登录失败：导游ID或密码错误");
                errorLabel.setText("导游ID或密码错误！");
            }
        } catch (Exception e) {
            logger.severe("登录过程中发生异常: " + e.getMessage());
            errorLabel.setText("登录失败：" + e.getMessage());        }
    }
}