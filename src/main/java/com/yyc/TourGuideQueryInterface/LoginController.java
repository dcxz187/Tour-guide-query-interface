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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    @FXML
    private void handleLogin() {
        String guideId = guideIdField.getText();
        String password = passwordField.getText();

        try {
            // 构造数据库用户名：guide_导游ID
            String dbUsername = "guide_" + guideId;

            // 使用该账号尝试连接数据库（验证是否存在且权限正确）
            Connection conn = DatabaseConnection.getConnection(dbUsername, password);

            if (isAuthenticationSuccessful(conn, guideId)) {
                // 登录成功，跳转主界面
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/yyc/TourGuideQueryInterface/main.fxml"));
                Parent root = loader.load();
                MainController mainController = loader.getController();
                mainController.setGuideId(guideId, password);

                Stage stage = (Stage) guideIdField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("导游查询系统 - 主界面");
            } else {
                errorLabel.setText("认证失败，请重试");
            }

        } catch (SQLException e) {
            logger.severe("数据库连接失败：" + e.getMessage());
            errorLabel.setText("数据库连接失败，请检查网络或账号密码");
        } catch (Exception e) {
            logger.severe("登录过程中发生异常：" + e.getMessage());
            errorLabel.setText("登录失败：" + e.getMessage());
        }
    }

    // 验证导游ID是否真实存在
    private boolean isAuthenticationSuccessful(Connection conn, String guideId) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT 1 FROM 员工 WHERE 导游号 = '" + guideId + "'")) {
            return rs.next();
        }
    }
}