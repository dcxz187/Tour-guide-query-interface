--  导游个人信息视图
CREATE VIEW 导游个人信息视图 AS
SELECT
    e.导游号 AS 导游ID,
    e.姓名 AS 导游姓名,
    e.导游资格等级 AS 资格等级,
    e.身份证号 AS 身份证号,
    b.分公司ID AS 分公司ID,
    b.名称 AS 分公司名称,
    b.办公地址 AS 分公司地址
FROM 员工 e
         JOIN 分公司 b ON e.分公司ID = b.分公司ID;

--  导游旅游团视图
CREATE VIEW 导游旅游团视图 AS
SELECT
    t.旅游团ID AS 旅游团ID,
    t.导游ID AS 导游ID,
    t.线路ID AS 线路ID,
    l.地点 AS 地点,
    l.景点 AS 景点,
    t.旅游时间 AS 旅游日期,
    l.服务等级 AS 服务等级,
    l.价格 AS 价格
FROM 旅游团 t
         JOIN 旅游线路 l ON t.线路ID = l.线路ID;

--  导游客户视图
CREATE VIEW 导游客户视图 AS
SELECT
    t.旅游团ID AS 旅游团ID,
    t.导游ID AS 导游ID,
    tc.客户ID AS 客户ID,
    c.姓名 AS 客户姓名,
    c.身份证号 AS 客户身份证号,
    c.职业 AS 客户职业
FROM 旅游团 t
         JOIN 旅游团_客户 tc ON t.旅游团ID = tc.旅游团ID
         JOIN 客户 c ON tc.客户ID = c.客户ID;

-- 导游合同视图
CREATE VIEW 导游合同视图 AS
SELECT
    c.合同ID AS 合同ID,
    c.导游ID AS 导游ID,
    c.线路ID AS 线路ID,
    l.地点 AS 地点,
    c.服务等级 AS 服务等级,
    c.保险信息 AS 保险信息,
    c.费用约定 AS 费用,
    c.旅游时间 AS 旅游时间
FROM 合同 c
         JOIN 旅游线路 l ON c.线路ID = l.线路ID;

--  导游业绩视图
CREATE VIEW 导游业绩视图 AS
SELECT
    f.关联ID AS 导游ID,
    f.统计月份 AS 统计月份,
    SUM(f.金额) AS 业绩金额
FROM 财务账目 f
WHERE f.类型 = '分类账' AND f.关联ID IN (SELECT 导游号 FROM 员工)
GROUP BY f.关联ID, f.统计月份;