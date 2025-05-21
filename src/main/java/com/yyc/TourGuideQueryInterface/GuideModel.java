package com.yyc.TourGuideQueryInterface;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuideModel {
    // 验证导游登录
    public boolean authenticate(String guideId, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(guideId, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT 密码哈希 FROM 导游用户 WHERE 导游ID = ?")) {

            stmt.setString(1, guideId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("密码哈希");
                return BCrypt.checkpw(password, hashedPassword);
            }
            return false;
        }
    }

    // 通用查询方法（支持日期过滤）
    public List<Map<String, Object>> query(String viewName, String guideId, Connection conn, LocalDate startDate, LocalDate endDate) throws SQLException {
        // 白名单校验视图名称
        if (!isValidViewName(viewName)) {
            throw new IllegalArgumentException("不允许查询的视图: " + viewName);
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(viewName);

        // 判断是否需要添加导游ID条件
        boolean needsGuideId = List.of(
                "导游个人信息视图",
                "导游旅游团视图",
                "导游客户视图",
                "导游合同视图",
                "导游业绩视图"
        ).contains(viewName);

        // 构建 WHERE 子句
        if (needsGuideId) {
            sql.append(" WHERE 导游ID = ?");
        }

        // 添加时间范围
        if (startDate != null && endDate != null) {
            if (needsGuideId) {
                sql.append(" AND 统计月份 BETWEEN ? AND ?");
            } else {
                sql.append(" WHERE 统计月份 BETWEEN ? AND ?");
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (needsGuideId) {
                stmt.setString(paramIndex++, guideId);
            }

            if (startDate != null && endDate != null) {
                stmt.setDate(paramIndex++, Date.valueOf(startDate));
                stmt.setDate(paramIndex, Date.valueOf(endDate));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                List<Map<String, Object>> result = new ArrayList<>();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    result.add(row);
                }

                return result;
            }
        }
    }



    // 白名单验证方法
    private boolean isValidViewName(String viewName) {
        List<String> allowedViews = List.of("导游业绩视图", "导游个人信息视图"
                , "导游合同视图", "导游客户视图", "导游旅游团视图", "分公司业绩视图", "客户消费视图", "线路收入视图"); // 允许的视图列表
        return allowedViews.contains(viewName);
    }


    // 导出查询结果为CSV
    public void exportToCSV(List<Map<String, Object>> data, String filePath) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("没有可导出的数据");
        }

        List<String> headers = new ArrayList<>(data.get(0).keySet());

        Path path = Paths.get(filePath);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path));
             CSVPrinter printer = new CSVPrinter(
                     new OutputStreamWriter(out, StandardCharsets.UTF_8), // 使用 UTF-8
                     CSVFormat.DEFAULT.builder()
                             .setHeader(headers.toArray(new String[0]))
                             .build())) {

            // 写入 UTF-8 BOM（必须放在 writer 创建之前）
            out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });

            for (Map<String, Object> row : data) {
                List<Object> rowData = new ArrayList<>();
                for (String col : headers) {
                    rowData.add(row.get(col));
                }
                printer.printRecord(rowData);
            }
        }
    }

}
