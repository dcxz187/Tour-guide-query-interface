-- 创建导游用户
CREATE USER 'guide_G001'@'localhost' IDENTIFIED BY 'G001';
CREATE USER 'guide_G002'@'localhost' IDENTIFIED BY 'G002';

-- 授予视图查询权限
GRANT SELECT ON tourism_db.导游个人信息视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游旅游团视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游客户视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游合同视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游业绩视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.分公司业绩视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.客户消费视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.线路收入视图 TO 'guide_G001'@'localhost';

GRANT SELECT ON tourism_db.导游个人信息视图 TO 'guide_G002'@'localhost';
GRANT SELECT ON tourism_db.导游旅游团视图 TO 'guide_G002'@'localhost';
GRANT SELECT ON tourism_db.导游客户视图 TO 'guide_G002'@'localhost';
GRANT SELECT ON tourism_db.导游合同视图 TO 'guide_G002'@'localhost';
GRANT SELECT ON tourism_db.导游业绩视图 TO 'guide_G002'@'localhost';

GRANT SELECT ON `tourism_db`.`员工` TO 'guide_G001'@'localhost';
GRANT SELECT ON `tourism_db`.`员工` TO 'guide_G002'@'localhost';

-- 分公司
GRANT SELECT ON tourism_db.分公司 TO 'guide_G001'@'localhost';

-- 合同
GRANT SELECT ON tourism_db.合同 TO 'guide_G001'@'localhost';

-- 员工
GRANT SELECT ON tourism_db.员工 TO 'guide_G001'@'localhost';

-- 客户
GRANT SELECT ON tourism_db.客户 TO 'guide_G001'@'localhost';

-- 导游用户
GRANT SELECT ON tourism_db.导游用户 TO 'guide_G001'@'localhost';

-- 旅游信息
GRANT SELECT ON tourism_db.旅游信息 TO 'guide_G001'@'localhost';

-- 旅游团
GRANT SELECT ON tourism_db.旅游团 TO 'guide_G001'@'localhost';

-- 旅游团_客户
GRANT SELECT ON tourism_db.旅游团_客户 TO 'guide_G001'@'localhost';

-- 旅游线路
GRANT SELECT ON tourism_db.旅游线路 TO 'guide_G001'@'localhost';

-- 财务账目
GRANT SELECT ON tourism_db.财务账目 TO 'guide_G001'@'localhost';

-- 刷新权限
FLUSH PRIVILEGES;

-- 分公司
GRANT SELECT ON tourism_db.分公司 TO 'guide_G002'@'localhost';

-- 合同
GRANT SELECT ON tourism_db.合同 TO 'guide_G002'@'localhost';

-- 员工
GRANT SELECT ON tourism_db.员工 TO 'guide_G002'@'localhost';

-- 客户
GRANT SELECT ON tourism_db.客户 TO 'guide_G002'@'localhost';

-- 导游用户
GRANT SELECT ON tourism_db.导游用户 TO 'guide_G002'@'localhost';

-- 旅游信息
GRANT SELECT ON tourism_db.旅游信息 TO 'guide_G002'@'localhost';

-- 旅游团
GRANT SELECT ON tourism_db.旅游团 TO 'guide_G002'@'localhost';

-- 旅游团_客户
GRANT SELECT ON tourism_db.旅游团_客户 TO 'guide_G002'@'localhost';

-- 旅游线路
GRANT SELECT ON tourism_db.旅游线路 TO 'guide_G002'@'localhost';

-- 财务账目
GRANT SELECT ON tourism_db.财务账目 TO 'guide_G002'@'localhost';

-- 刷新权限
FLUSH PRIVILEGES;

GRANT SELECT ON tourism_db.* TO 'guide_G001'@'localhost';