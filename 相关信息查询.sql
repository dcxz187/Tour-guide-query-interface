-- 查询未来一个月的旅游团安排
SELECT 旅游团ID, 线路ID, 地点, 景点, 旅游日期, 服务等级
FROM 导游旅游团视图
WHERE 导游ID = 'G001'
  AND 旅游日期 BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH);

-- 查询某旅游团的客户和合同信息
SELECT g.旅游团ID, g.客户ID, g.客户姓名, c.合同ID, c.费用, c.保险信息
FROM 导游客户视图 g
         JOIN 旅游信息 ti ON g.客户ID = ti.客户ID
         JOIN 导游合同视图 c ON ti.合同ID = c.合同ID
WHERE g.导游ID = 'G001' AND g.旅游团ID = 'TG001';

-- 查询年度业绩汇总
SELECT 统计月份, 业绩金额
FROM 导游业绩视图
WHERE 导游ID = 'G001' AND YEAR(统计月份) = 2024
ORDER BY 统计月份;

-- 创建导游用户
CREATE USER 'guide_G001'@'localhost' IDENTIFIED BY 'G001';

-- 授予视图查询权限
GRANT SELECT ON tourism_db.导游个人信息视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游旅游团视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游客户视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游合同视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游业绩视图 TO 'guide_G001'@'localhost';