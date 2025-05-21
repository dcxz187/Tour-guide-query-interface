package com.yyc.TourGuideQueryInterface;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class MainController {
    // 添加日志记录器
    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    @FXML
    public Button profileButton;
    @FXML
    public Button tourGroupButton;
    @FXML
    public Button clientButton;
    @FXML
    public Button contractButton;
    @FXML
    public Button performanceButton;
    @FXML
    public Button clientConsumptionButton;
    @FXML
    public Button routeIncomeButton;
    @FXML
    public Button branchPerformanceButton;
    @FXML
    private TableView<Map<String, Object>> resultTable;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button exportButton;
    @FXML
    private ComboBox<String> themeCombo;
    @FXML
    private Label statusLabel;

    private String guideId;
    private Connection userConnection;

    public void setGuideId(String guideId, String password) {
        this.guideId = guideId;

        try {
            String dbUsername = "guide_" + guideId;
            this.userConnection = DatabaseConnection.getConnection(dbUsername, password);
        } catch (SQLException e) {
            // 处理连接失败
            // 使用日志记录器记录异常信息
            logger.severe("数据库连接失败: " + e.getMessage());
            logger.throwing(MainController.class.getName(), "setGuideId", e);
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("连接失败");
        alert.setHeaderText(null);
        alert.setContentText("无法连接到数据库，请检查账号权限");
        alert.showAndWait();
    }


    private final GuideModel model = new GuideModel();

    @FXML
    private void initialize() {
        // 初始化主题下拉框
        themeCombo.setItems(FXCollections.observableArrayList("浅色主题", "深色主题"));
        themeCombo.setOnAction(e -> switchTheme(themeCombo.getValue()));

        // 导出按钮
        exportButton.setOnAction(e -> exportToCSV());
    }

    @FXML
    private void queryProfile() {
        query("导游个人信息视图", null, null);
    }

    @FXML
    private void queryTourGroups() {
        query("导游旅游团视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryClients() {
        query("导游客户视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryContracts() {
        query("导游合同视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryPerformance() {
        query("导游业绩视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryBranchPerformance() {
        query("分公司业绩视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryClientConsumption() {
        query("客户消费视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    @FXML
    private void queryRouteIncome() {
        query("线路收入视图", startDatePicker.getValue(), endDatePicker.getValue());
    }

    private String currentViewName;

    private void query(String viewName, LocalDate startDate, LocalDate endDate) {
        this.currentViewName = viewName;
        try {
            List<Map<String, Object>> data = model.query(viewName, guideId, userConnection, startDate, endDate);

            resultTable.getColumns().clear();
            resultTable.getItems().clear();

            if (!data.isEmpty()) {
                // 动态创建列
                Map<String, Object> firstRow = data.get(0);
                for (String colName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> col = new TableColumn<>(colName);
                    final String name = colName;
                    col.setCellValueFactory(cellData -> new SimpleStringProperty(
                            cellData.getValue().get(name) != null ? cellData.getValue().get(name).toString() : ""));
                    resultTable.getColumns().add(col);
                }

                // 添加数据
                resultTable.getItems().addAll(data);
                statusLabel.setText("查询成功，共 " + data.size() + " 条记录");
            } else {
                statusLabel.setText("没有找到符合条件的数据");
            }
        } catch (Exception e) {
            String errorMessage = e.getMessage();

            // 判断是否是权限相关错误（根据数据库驱动返回的信息判断）
            if (errorMessage.contains("command denied") || errorMessage.contains("权限") || errorMessage.contains("SQLSyntaxErrorException")) {
                statusLabel.setText("无权查看该视图");
            } else {
                statusLabel.setText("查询失败：" + e.getMessage());
            }

            logger.severe("查询过程中发生异常: " + e.getMessage());
            logger.throwing(MainController.class.getName(), "query", e);
        }
    }

    @FXML
    private void exportToCSV() {
        if (currentViewName == null || resultTable.getItems().isEmpty()) {
            Platform.runLater(() -> statusLabel.setText("没有可导出的数据"));
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存CSV文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV文件", "*.csv"));
        java.io.File file = fileChooser.showSaveDialog(resultTable.getScene().getWindow());

        if (file != null) {
            new Thread(() -> {
                try {
                    List<Map<String, Object>> data = model.query(
                            currentViewName,
                            guideId,
                            userConnection,
                            startDatePicker.getValue(),
                            endDatePicker.getValue()
                    );
                    model.exportToCSV(data, file.getAbsolutePath());
                    Platform.runLater(() -> statusLabel.setText("导出成功：" + file.getAbsolutePath()));
                } catch (Exception e) {
                    Platform.runLater(() -> statusLabel.setText("导出失败：" + e.getMessage()));
                }
            }).start();
        }
    }

    @FXML
    private BorderPane mainPane;
    private void switchTheme(String theme) {
        String css = theme.equals("深色主题") ?
                Objects.requireNonNull(getClass().getResource("/dark-theme.css")).toExternalForm() :
                Objects.requireNonNull(getClass().getResource("/light-theme.css")).toExternalForm();
        mainPane.getScene().getStylesheets().clear();
        mainPane.getScene().getStylesheets().add(css);
        statusLabel.setText("已切换至 " + theme);
    }
}