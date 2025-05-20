package com.yyc.travelsystem;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

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
    private final GuideModel model = new GuideModel();

    @FXML
    private void initialize() {
        // 初始化主题下拉框
        themeCombo.setItems(FXCollections.observableArrayList("浅色主题", "深色主题"));
        themeCombo.setOnAction(e -> switchTheme(themeCombo.getValue()));

        // 导出按钮
        exportButton.setOnAction(e -> exportToCSV());
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
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

    private void query(String viewName, LocalDate startDate, LocalDate endDate) {
        try {
            List<Map<String, Object>> data = model.query(viewName, guideId, startDate, endDate);

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
            statusLabel.setText("查询失败：" + e.getMessage());
            logger.severe("查询过程中发生异常: " + e.getMessage());  // 替换为日志记录
            logger.throwing(MainController.class.getName(), "query", e); // 可选：记录异常堆栈
        }
    }


    @FXML
    private void exportToCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存CSV文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV文件", "*.csv"));
        java.io.File file = fileChooser.showSaveDialog(resultTable.getScene().getWindow());

        if (file != null) {
            new Thread(() -> {
                try {
                    // 获取当前表格列名作为视图名称（或从上下文取）
                    String viewName = resultTable.getColumns().get(0).getText();

                    // 查询数据（改用 List<Map<String, Object>>）
                    List<Map<String, Object>> data = model.query(
                            viewName,
                            guideId,
                            startDatePicker.getValue(),
                            endDatePicker.getValue()
                    );

                    // 导出为 CSV
                    model.exportToCSV(data, file.getAbsolutePath());

                    // 更新 UI 状态
                    Platform.runLater(() -> statusLabel.setText("导出成功：" + file.getAbsolutePath()));

                } catch (Exception e) {
                    Platform.runLater(() -> statusLabel.setText("导出失败：" + e.getMessage()));
                    logger.warning("导出过程中发生异常: " + e.getMessage());  // 替换为日志记录
                    logger.throwing(MainController.class.getName(), "exportToCSV", e); // 可选：记录异常堆栈
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
    }
}