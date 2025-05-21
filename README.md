## **旅游公司数据库设计**

---

### **(1) 分析业务流程，绘制数据流图（DFD），编写数据字典，补充对象和属性信息**

#### **业务流程分析**
旅游公司由多个分公司组成，每个分公司有经理、导游和客户。主要业务流程包括：
- **员工管理**：记录导游信息（导游号、身份证号、姓名、导游资格等级等）。
- **旅游线路管理**：维护旅游线路信息（地点、景点、时间段、价格、交通方式、服务等级等）。
- **客户管理**：存储客户基本信息（姓名、身份证号、工作单位、职业等）及旅游信息（旅游时间、线路、费用、保险、服务等级、合同等）。
- **旅游团管理**：导游负责旅游团，每团有多个客户。
- **合同管理**：每次旅游需签订合同，记录线路、导游、服务等级、保险、费用、时间等。
- **财务管理**：按月统计线路收入、客户消费、导游业绩（分类账）及分公司销售业绩（总账）。

#### **数据流图（DFD）**
**0级DFD（上下文图）**：
- **外部实体**：客户、导游、经理、财务审计员。
- **主流程**：旅游管理系统。
- **数据流**：
    - 客户 → 系统：客户信息、旅游请求、合同签名。
    - 系统 → 客户：线路详情、合同、账单。
    - 导游 → 系统：可用性、旅游团分配。
    - 系统 → 导游：旅游日程、客户列表。
    - 经理 → 系统：分公司信息、员工记录。
    - 系统 → 财务审计员：分类账、总账报表。

**1级DFD**：

- **子流程**：
    1. 管理员工数据（存储导游/经理信息）。
    2. 管理旅游线路（维护线路、景点、时间段等）。
    3. 管理客户数据（记录客户信息及旅游历史）。
    4. 分配旅游团（导游与客户关联）。
    5. 生成合同（记录旅游细节）。
    6. 财务统计（生成分类账和总账）。
- **数据存储**：员工库、线路库、客户库、合同库、财务库。
- **数据流**：各子流程与数据存储、外部实体之间的交互。

#### **数据字典**
以下为主要数据项的定义（部分示例，完整字典可扩展）：
- **员工**：
    - 导游号：唯一标识，字符串，主键。
    - 身份证号：字符串，唯一。
    - 姓名：字符串。
    - 导游资格等级：枚举（如初级、中级、高级）。
    - 分公司ID：外键，关联分公司。
- **旅游线路**：
    - 线路ID：唯一标识，字符串，主键。
    - 地点：字符串列表。
    - 景点：字符串列表。
    - 时间段：日期范围。
    - 价格：数值，按时间段和服务等级。
    - 交通方式：字符串（如飞机、火车）。
    - 服务等级：枚举（如标准、豪华）。
- **客户**：
    - 客户ID：唯一标识，字符串，主键。
    - 身份证号：字符串，唯一。
    - 姓名：字符串。
    - 工作单位：字符串，可空。
    - 职业：字符串。
- **旅游信息**：
    - 旅游ID：唯一标识，字符串，主键。
    - 客户ID：外键。
    - 线路ID：外键。
    - 旅游时间：日期。
    - 费用：数值。
    - 保险：字符串（如保险类型）。
    - 服务等级：枚举。
    - 合同ID：外键。
- **合同**：
    - 合同ID：唯一标识，字符串，主键。
    - 版本号：字符串。
    - 线路ID：外键。
    - 导游ID：外键。
    - 服务等级：枚举。
    - 保险信息：字符串。
    - 费用约定：数值。
    - 旅游时间：日期。
- **旅游团**：
    - 旅游团ID：唯一标识，字符串，主键。
    - 导游ID：外键。
    - 线路ID：外键。
    - 客户ID列表：外键集合。
    - 旅游时间：日期。
- **财务账目**：
    - 账目ID：唯一标识，字符串，主键。
    - 类型：枚举（分类账、总账）。
    - 统计月份：日期。
    - 线路ID/客户ID/导游ID/分公司ID：外键（视账目类型）。
    - 金额：数值。

#### **补充信息**
- **分公司**：新增属性：分公司ID（主键）、名称、办公地址、经理ID（外键，关联员工）。
- **旅游时间段**：明确为日期范围（如2025-06-01至2025-06-07）。
- **服务等级**：定义为枚举值（标准、高级、豪华）。
- **保险信息**：包括保险类型（如意外险、医疗险）和保额。

---

### **(2) 分析实体及其关系，确定实体对象和属性，绘制E-R图，指定关键属性**

#### **实体及其属性**
1. **分公司**：
    - 属性：分公司ID（主键）、名称、办公地址、经理ID。
2. **员工**：
    - 属性：导游号（主键）、身份证号、姓名、导游资格等级、分公司ID。
3. **旅游线路**：
    - 属性：线路ID（主键）、地点、景点、时间段、价格、交通方式、服务等级。
4. **客户**：
    - 属性：客户ID（主键）、身份证号、姓名、工作单位、职业。
5. **旅游信息**：
    - 属性：旅游ID（主键）、客户ID、线路ID、旅游时间、费用、保险、服务等级、合同ID。
6. **合同**：
    - 属性：合同ID（主键）、版本号、线路ID、导游ID、服务等级、保险信息、费用约定、旅游时间。
7. **旅游团**：
    - 属性：旅游团ID（主键）、导游ID、线路ID、旅游时间。
8. **财务账目**：
    - 属性：账目ID（主键）、类型、统计月份、关联ID（线路ID/客户ID/导游ID/分公司ID）、金额。

#### **实体关系**
- **分公司-员工**：1:N（一个分公司有多个员工）。
- **员工-旅游团**：1:N（一个导游负责多个旅游团）。
- **旅游线路-旅游团**：1:N（一条线路可用于多个旅游团）。
- **客户-旅游信息**：1:N（一个客户有多次旅游记录）。
- **旅游团-客户**：N:N（一个旅游团有多个客户，一个客户可参加多个旅游团）。
- **旅游信息-合同**：1:1（一次旅游对应一个合同）。
- **旅游线路-合同**：1:N（一条线路可对应多个合同）。
- **财务账目**：与线路、客户、导游、分公司有关，视账目类型而定。

#### **E-R图**
（由于文本限制，描述E-R图结构，实际需用工具如Visio绘制）
- **实体**：用矩形表示（如“分公司”“员工”）。
- **属性**：用椭圆连接到实体，主键加下划线（如“分公司ID”）。
- **关系**：用菱形表示，标注关系类型（如“1:N”）。
    - 分公司 → 员工：1:N，连接“分公司ID”。
    - 员工 → 旅游团：1:N，连接“导游号”。
    - 客户 ↔ 旅游团：N:N，通过中间实体“旅游团-客户”实现。
    - 旅游信息 → 合同：1:1，连接“合同ID”。
    - 财务账目与相关实体通过“关联ID”连接。

#### **关键属性**
- 分公司：分公司ID。
- 员工：导游号。
- 旅游线路：线路ID。
- 客户：客户ID。
- 旅游信息：旅游ID。
- 合同：合同ID。
- 旅游团：旅游团ID。
- 财务账目：账目ID。

---

### **(3) 将E-R图转换为关系数据库模型，定义完整性约束**

#### **关系模型**
1. **分公司**：
    - 关系：分公司(分公司ID, 名称, 办公地址, 经理ID)
    - 主键：分公司ID
2. **员工**：
    - 关系：员工(导游号, 身份证号, 姓名, 导游资格等级, 分公司ID)
    - 主键：导游号
3. **旅游线路**：
    - 关系：旅游线路(线路ID, 地点, 景点, 时间段, 价格, 交通方式, 服务等级)
    - 主键：线路ID
4. **客户**：
    - 关系：客户(客户ID, 身份证号, 姓名, 工作单位, 职业)
    - 主键：客户ID
5. **旅游信息**：
    - 关系：旅游信息(旅游ID, 客户ID, 线路ID, 旅游时间, 费用, 保险, 服务等级, 合同ID)
    - 主键：旅游ID
6. **合同**：
    - 关系：合同(合同ID, 版本号, 线路ID, 导游ID, 服务等级, 保险信息, 费用约定, 旅游时间)
    - 主键：合同ID
7. **旅游团**：
    - 关系：旅游团(旅游团ID, 导游ID, 线路ID, 旅游时间)
    - 主键：旅游团ID
8. **旅游团-客户**（N:N关系中间表）：
    - 关系：旅游团_客户(旅游团ID, 客户ID)
    - 主键：(旅游团ID, 客户ID)
9. **财务账目**：
    - 关系：财务账目(账目ID, 类型, 统计月份, 关联ID, 金额)
    - 主键：账目ID

#### **完整性约束**
1. **实体完整性**：
    - 每个关系的主键非空且唯一（如分公司ID、导游号等）。
2. **参照完整性**：
    - 员工.分公司ID → 分公司.分公司ID
    - 员工.经理ID → 员工.导游号
    - 旅游信息.客户ID → 客户.客户ID
    - 旅游信息.线路ID → 旅游线路.线路ID
    - 旅游信息.合同ID → 合同.合同ID
    - 合同.线路ID → 旅游线路.线路ID
    - 合同.导游ID → 员工.导游号
    - 旅游团.导游ID → 员工.导游号
    - 旅游团.线路ID → 旅游线路.线路ID
    - 旅游团_客户.旅游团ID → 旅游团.旅游团ID
    - 旅游团_客户.客户ID → 客户.客户ID
    - 财务账目.关联ID → 对应实体主键（视类型，如线路ID、客户ID等）。
3. **用户定义完整性**：
    - 导游资格等级：限制为枚举值（初级、中级、高级）。
    - 服务等级：限制为枚举值（标准、高级、豪华）。
    - 身份证号：唯一且符合格式（如18位）。
    - 价格、费用：非负数值。
    - 旅游时间、统计月份：有效日期格式。

---

### **(4) 用BCNF和3NF理论分析优化关系模型**

#### **规范化分析**
检查每个关系是否满足**3NF**（无传递依赖）和**BCNF**（所有决定因素为主键）。

1. **分公司**：
    - 函数依赖：分公司ID → 名称, 办公地址, 经理ID
    - 满足BCNF（决定因素分公司ID为主键）。
2. **员工**：
    - 函数依赖：导游号 → 身份证号, 姓名, 导游资格等级, 分公司ID
    - 满足BCNF。
3. **旅游线路**：
    - 函数依赖：线路ID → 地点, 景点, 时间段, 价格, 交通方式, 服务等级
    - 满足BCNF。
4. **客户**：
    - 函数依赖：客户ID → 身份证号, 姓名, 工作单位, 职业
    - 满足BCNF。
5. **旅游信息**：
    - 函数依赖：旅游ID → 客户ID, 线路ID, 旅游时间, 费用, 保险, 服务等级, 合同ID
    - 满足BCNF。
6. **合同**：
    - 函数依赖：合同ID → 版本号, 线路ID, 导游ID, 服务等级, 保险信息, 费用约定, 旅游时间
    - 满足BCNF。
7. **旅游团**：
    - 函数依赖：旅游团ID → 导游ID, 线路ID, 旅游时间
    - 满足BCNF。
8. **旅游团_客户**：
    - 函数依赖：(旅游团ID, 客户ID) → 无其他属性
    - 满足BCNF。
9. **财务账目**：
    - 函数依赖：账目ID → 类型, 统计月份, 关联ID, 金额
    - 满足BCNF。

#### **优化**
- 所有关系已满足BCNF，无需分解。
- **潜在问题**：旅游线路的“地点”“景点”可能是多值属性，可能需要拆分为独立关系：
    - 地点表：(线路ID, 地点)
    - 景点表：(线路ID, 景点)
    - 这样可避免存储重复的列表数据，符合3NF。
- **索引优化**：
    - 为频繁查询的外键（如客户ID、线路ID）创建索引。
    - 为财务账目的统计月份创建索引以加速按月统计。

---

### **(5) 分析数据规模，找出大关系，提出存储结构和策略**

#### **数据规模分析**
- **分公司**：数量较少（假设10-50个），数据规模小。
- **员工**：每分公司10-50名导游，假设50分公司，总计500-2500行，中等规模。
- **旅游线路**：每分公司10-20条线路，总计500-1000行，中等规模。
- **客户**：假设每年服务10万客户，数据规模大。
- **旅游信息**：每个客户平均2次旅游，总计20万行，**大关系**。
- **合同**：与旅游信息1:1，总计20万行，**大关系**。
- **旅游团**：假设每月100个团，10年约1.2万行，中等规模。
- **旅游团_客户**：每个团20-50人，总计40万行，**大关系**。
- **财务账目**：按月统计线路、客户、导游、分公司，约10万行，**大关系**。

#### **大关系**
- **旅游信息**（20万行）
- **合同**（20万行）
- **旅游团_客户**（40万行）
- **财务账目**（10万行）

#### **存储结构和策略**
1. **分区存储**：
    - **旅游信息、合同**：按旅游时间分区（如按年：2023、2024、2025），加速时间范围查询。
    - **财务账目**：按统计月份分区（如2025-01、2025-02）。
    - **旅游团_客户**：按旅游团ID分区，减少单表扫描。
2. **索引**：
    - 旅游信息：客户ID、线路ID、旅游时间。
    - 合同：线路ID、导游ID。
    - 旅游团_客户：旅游团ID、客户ID。
    - 财务账目：统计月份、关联ID。
3. **压缩**：
    - 对历史数据（如3年前的旅游信息、合同）使用列存储或压缩格式，降低存储成本。
4. **分片**：
    - 若数据量持续增长，可按分公司ID分片存储客户、旅游信息等表，分布式部署。
5. **缓存**：
    - 对热门线路和频繁查询的客户信息使用内存缓存（如Redis），减少数据库压力。

---

### **(6) 用SQL实现数据库设计，填充实验数据**

#### **SQL建表**
以下为主要表的SQL定义：

建表.sql

```sql
-- 员工 (暂不加对分公司的外键)
CREATE TABLE 员工 (
                      导游号 VARCHAR(10) PRIMARY KEY,
                      身份证号 VARCHAR(18) UNIQUE NOT NULL,
                      姓名 VARCHAR(50) NOT NULL,
                      导游资格等级 ENUM('初级', '中级', '高级') NOT NULL,
                      分公司ID VARCHAR(10)
);

-- 分公司
CREATE TABLE 分公司 (
                        分公司ID VARCHAR(10) PRIMARY KEY,
                        名称 VARCHAR(50) NOT NULL,
                        办公地址 VARCHAR(100),
                        经理ID VARCHAR(10),
                        FOREIGN KEY (经理ID) REFERENCES 员工(导游号)
);

-- 后续添加员工表对外键的引用
ALTER TABLE 员工
    ADD CONSTRAINT fk_分公司ID FOREIGN KEY (分公司ID) REFERENCES 分公司(分公司ID);

-- 旅游线路
CREATE TABLE 旅游线路 (
                          线路ID VARCHAR(10) PRIMARY KEY,
                          地点 TEXT,
                          景点 TEXT,
                          时间段 VARCHAR(50),
                          价格 DECIMAL(10,2),
                          交通方式 VARCHAR(50),
                          服务等级 ENUM('标准', '高级', '豪华')
);

-- 客户
CREATE TABLE 客户 (
                      客户ID VARCHAR(10) PRIMARY KEY,
                      身份证号 VARCHAR(18) UNIQUE NOT NULL,
                      姓名 VARCHAR(50) NOT NULL,
                      工作单位 VARCHAR(100),
                      职业 VARCHAR(50)
);

-- 合同
CREATE TABLE 合同 (
                      合同ID VARCHAR(10) PRIMARY KEY,
                      版本号 VARCHAR(10),
                      线路ID VARCHAR(10),
                      导游ID VARCHAR(10),
                      服务等级 ENUM('标准', '中级', '高级') NOT NULL,
                      保险信息 VARCHAR(50),
                      费用约定 DECIMAL(10,2),
                      旅游时间 DATE,
                      FOREIGN KEY (线路ID) REFERENCES 旅游线路(线路ID),
                      FOREIGN KEY (导游ID) REFERENCES 员工(导游号)
);

-- 旅游信息
CREATE TABLE 旅游信息 (
                          旅游ID VARCHAR(10) PRIMARY KEY,
                          客户ID VARCHAR(10),
                          线路ID VARCHAR(10),
                          旅游时间 DATE,
                          费用 DECIMAL(10,2),
                          保险 VARCHAR(50),
                          服务等级 ENUM('标准', '高级', '豪华'),
                          合同ID VARCHAR(10),
                          FOREIGN KEY (客户ID) REFERENCES 客户(客户ID),
                          FOREIGN KEY (线路ID) REFERENCES 旅游线路(线路ID),
                          FOREIGN KEY (合同ID) REFERENCES 合同(合同ID)
);

-- 旅游团
CREATE TABLE 旅游团 (
                        旅游团ID VARCHAR(10) PRIMARY KEY,
                        导游ID VARCHAR(10),
                        线路ID VARCHAR(10),
                        旅游时间 DATE,
                        FOREIGN KEY (导游ID) REFERENCES 员工(导游号),
                        FOREIGN KEY (线路ID) REFERENCES 旅游线路(线路ID)
);

-- 旅游团_客户
CREATE TABLE 旅游团_客户 (
                             旅游团ID VARCHAR(10),
                             客户ID VARCHAR(10),
                             PRIMARY KEY (旅游团ID, 客户ID),
                             FOREIGN KEY (旅游团ID) REFERENCES 旅游团(旅游团ID),
                             FOREIGN KEY (客户ID) REFERENCES 客户(客户ID)
);

-- 财务账目
CREATE TABLE 财务账目 (
                          账目ID VARCHAR(10) PRIMARY KEY,
                          类型 ENUM('分类账', '总账') NOT NULL,
                          统计月份 DATE NOT NULL,
                          关联ID VARCHAR(10),
                          金额 DECIMAL(10,2)
);

CREATE TABLE 导游用户 (
                          导游ID VARCHAR(10) PRIMARY KEY,
                          密码哈希 VARCHAR(60) NOT NULL,
                          FOREIGN KEY (导游ID) REFERENCES 员工(导游号)
);
```

#### **实验数据**
- **小表**（如分公司、员工、线路）：插入10+行。

- **大表**（如旅游信息、合同、旅游团_客户、财务账目）：插入1000+行。

  模拟数据.sql
```sql
USE tourism_db;

INSERT INTO 员工 (导游号, 身份证号, 姓名, 导游资格等级)
VALUES
    ('G001', '110101199003072516', '张伟', '高级'),
    ('G002', '110101198504123456', '王芳', '中级'),
    ('G003', '110101199206154321', '李娜', '初级'),
    ('G004', '110101198809221234', '赵强', '高级'),
    ('G005', '110101199312111111', '孙丽', '中级');

INSERT INTO 分公司 (分公司ID, 名称, 办公地址, 经理ID)
VALUES
    ('B001', '北京分公司', '北京市朝阳区建国路88号', 'G001'),
    ('B002', '上海分公司', '上海市浦东新区世纪大道100号', 'G002'),
    ('B003', '广州分公司', '广州市天河区珠江新城华夏路23号', 'G003'),
    ('B004', '成都分公司', '成都市武侯区人民南路四段55号', 'G004'),
    ('B005', '武汉分公司', '武汉市江汉区解放大道688号', 'G005');

UPDATE 员工 SET 分公司ID = 'B001' WHERE 导游号 = 'G001';
UPDATE 员工 SET 分公司ID = 'B002' WHERE 导游号 = 'G002';
UPDATE 员工 SET 分公司ID = 'B003' WHERE 导游号 = 'G003';
UPDATE 员工 SET 分公司ID = 'B001' WHERE 导游号 = 'G004';
UPDATE 员工 SET 分公司ID = 'B002' WHERE 导游号 = 'G005';


INSERT INTO 旅游线路 (线路ID, 地点, 景点, 时间段, 价格, 交通方式, 服务等级)
VALUES
    ('T001', '北京-天津', '故宫、长城、五大道', '3天2晚', 1500.00, '大巴', '标准'),
    ('T002', '上海-杭州', '外滩、西湖、灵隐寺', '4天3晚', 2200.00, '高铁', '高级'),
    ('T003', '成都-重庆', '宽窄巷子、磁器口、洪崖洞', '5天4晚', 1800.00, '飞机+大巴', '豪华'),
    ('T004', '广州-深圳', '白云山、世界之窗、欢乐谷', '2天1晚', 1000.00, '大巴', '标准'),
    ('T005', '武汉-长沙', '黄鹤楼、橘子洲头、岳麓山', '3天2晚', 1300.00, '高铁', '高级');

INSERT INTO 客户 (客户ID, 身份证号, 姓名, 工作单位, 职业)
VALUES
    ('C001', '110101198001011234', '刘洋', '腾讯科技有限公司', '软件工程师'),
    ('C002', '110101198202022345', '陈静', '阿里巴巴集团', '产品经理'),
    ('C003', '110101199003033456', '周杰', '华为技术有限公司', '网络工程师'),
    ('C004', '110101198504044567', '吴婷', '工商银行', '银行职员'),
    ('C005', '110101198805055678', '黄磊', '京东商城', '市场经理');

INSERT INTO 合同 (合同ID, 版本号, 线路ID, 导游ID, 服务等级, 保险信息, 费用约定, 旅游时间)
VALUES
    ('CT001', 'v1.0', 'T001', 'G001', '标准', '平安保险', 1500.00, '2024-06-10'),
    ('CT002', 'v1.1', 'T002', 'G002', '高级', '太平洋保险', 2200.00, '2024-07-15'),
    ('CT003', 'v1.0', 'T003', 'G003', '高级', '人保财险', 1800.00, '2024-08-20'),
    ('CT004', 'v1.2', 'T004', 'G004', '标准', '平安保险', 1000.00, '2024-09-10'),
    ('CT005', 'v1.0', 'T005', 'G005', '中级', '人寿保险', 1300.00, '2024-10-05');

INSERT INTO 旅游信息 (旅游ID, 客户ID, 线路ID, 旅游时间, 费用, 保险, 服务等级, 合同ID)
VALUES
    ('TR001', 'C001', 'T001', '2024-06-10', 1500.00, '平安保险', '标准', 'CT001'),
    ('TR002', 'C002', 'T002', '2024-07-15', 2200.00, '太平洋保险', '高级', 'CT002'),
    ('TR003', 'C003', 'T003', '2024-08-20', 1800.00, '人保财险', '豪华', 'CT003'),
    ('TR004', 'C004', 'T004', '2024-09-10', 1000.00, '平安保险', '标准', 'CT004'),
    ('TR005', 'C005', 'T005', '2024-10-05', 1300.00, '人寿保险', '高级', 'CT005');

INSERT INTO 旅游团 (旅游团ID, 导游ID, 线路ID, 旅游时间)
VALUES
    ('TG001', 'G001', 'T001', '2024-06-10'),
    ('TG002', 'G002', 'T002', '2024-07-15'),
    ('TG003', 'G003', 'T003', '2024-08-20'),
    ('TG004', 'G004', 'T004', '2024-09-10'),
    ('TG005', 'G005', 'T005', '2024-10-05');

INSERT INTO 旅游团_客户 (旅游团ID, 客户ID)
VALUES
    ('TG001', 'C001'),
    ('TG001', 'C002'),
    ('TG002', 'C003'),
    ('TG002', 'C004'),
    ('TG003', 'C005'),
    ('TG004', 'C001'),
    ('TG004', 'C002'),
    ('TG005', 'C003');

INSERT INTO 财务账目 (账目ID, 类型, 统计月份, 关联ID, 金额)
VALUES
    ('F001', '分类账', '2024-05-01', 'T001', 15000.00),
    ('F002', '总账', '2024-05-01', 'B001', 45000.00),
    ('F003', '分类账', '2024-05-01', 'T002', 22000.00),
    ('F004', '总账', '2024-05-01', 'B002', 30000.00),
    ('F005', '分类账', '2024-05-01', 'T003', 18000.00);

INSERT INTO 财务账目 (账目ID, 类型, 统计月份, 关联ID, 金额) VALUES
                                                                -- 分类账 - 线路
                                                                ('F010', '分类账', '2024-05-01', 'T001', 15000.00),
                                                                ('F011', '分类账', '2024-05-01', 'T002', 22000.00),
                                                                ('F012', '分类账', '2024-05-01', 'T003', 18000.00),
                                                                ('F013', '分类账', '2024-05-01', 'T004', 10000.00),
                                                                ('F014', '分类账', '2024-05-01', 'T005', 13000.00),

                                                                -- 总账 - 分公司
                                                                ('F020', '总账', '2024-05-01', 'B001', 45000.00),
                                                                ('F021', '总账', '2024-05-01', 'B002', 30000.00),
                                                                ('F022', '总账', '2024-05-01', 'B003', 35000.00),
                                                                ('F023', '总账', '2024-05-01', 'B004', 28000.00),
                                                                ('F024', '总账', '2024-05-01', 'B005', 22000.00),

                                                                -- 分类账 - 导游业绩
                                                                ('F030', '分类账', '2024-05-01', 'G001', 5000.00),
                                                                ('F031', '分类账', '2024-05-01', 'G002', 4500.00),
                                                                ('F032', '分类账', '2024-05-01', 'G003', 4000.00),
                                                                ('F033', '分类账', '2024-05-01', 'G004', 3500.00),
                                                                ('F034', '分类账', '2024-05-01', 'G005', 3000.00),

                                                                -- 分类账 - 客户消费
                                                                ('F040', '分类账', '2024-05-01', 'C001', 1500.00),
                                                                ('F041', '分类账', '2024-05-01', 'C002', 2200.00),
                                                                ('F042', '分类账', '2024-05-01', 'C003', 1800.00),
                                                                ('F043', '分类账', '2024-05-01', 'C004', 1000.00),
                                                                ('F044', '分类账', '2024-05-01', 'C005', 1300.00),

                                                                -- 跨月数据
                                                                ('F050', '分类账', '2024-06-01', 'T001', 16000.00),
                                                                ('F051', '分类账', '2024-06-01', 'G001', 5500.00),
                                                                ('F052', '分类账', '2024-06-01', 'C001', 1700.00),
                                                                ('F053', '总账', '2024-06-01', 'B001', 40000.00);

```
**生成大表数据**：

- 使用Python脚本或SQL存储过程生成1000+行数据，随机分配客户ID、线路ID、日期等。
- 示例Python脚本：
```python
import random
import sqlite3

conn = sqlite3.connect('tourism.db')
cursor = conn.cursor()

for i in range(1000):
    tid = f'T{str(i+1).zfill(4)}'
    cid = f'C{str(random.randint(1, 1000)).zfill(4)}'
    lid = f'L{str(random.randint(1, 100)).zfill(4)}'
    date = f'2025-{random.randint(1,12):02d}-{random.randint(1,28):02d}'
    fee = random.randint(2000, 10000)
    insurance = random.choice(['意外险', '医疗险'])
    level = random.choice(['标准', '高级', '豪华'])
    ctid = f'CT{str(i+1).zfill(4)}'
    cursor.execute('INSERT INTO 旅游信息 VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
                   (tid, cid, lid, date, fee, insurance, level, ctid))

conn.commit()
conn.close()
```

#### **实现**
- 使用MySQL、PostgreSQL等数据库系统。
- 创建数据库`tourism_db`，执行建表SQL，插入数据。
- 验证数据完整性（如外键约束、唯一性）。

---

### **(7) 用SQL统计数据，形成分类账和总账**

#### **分类账**
- **线路收入**：
```sql
SELECT
    关联ID AS 线路ID,
    统计月份,
    SUM(金额) as 线路收入
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 线路ID FROM 旅游线路)
GROUP BY 关联ID, 统计月份;
```
- **客户消费**：
```sql
SELECT
    客户ID,
    统计月份,
    SUM(费用) as 消费金额
FROM 旅游信息 t
         JOIN 财务账目 f ON t.旅游ID = f.关联ID
WHERE f.类型 = '分类账'
GROUP BY 客户ID, 统计月份;
```
- **导游业绩**：
```sql
SELECT
    关联ID AS 导游ID,
    统计月份,
    SUM(金额) AS 业绩金额
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 导游号 FROM 员工)
GROUP BY 关联ID, 统计月份;
```

#### **总账**
- **分公司销售业绩**：
```sql
SELECT
    关联ID AS 分公司ID,
    统计月份,
    SUM(金额) as 业绩金额
FROM 财务账目
WHERE 类型 = '总账' AND 关联ID IN (SELECT 分公司ID FROM 分公司)
GROUP BY 关联ID, 统计月份;
```

#### **实现**
- 创建视图存储统计结果：
```sql
CREATE VIEW 线路收入视图 AS
SELECT
    关联ID AS 线路ID,
    统计月份,
    SUM(金额) as 线路收入
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 线路ID FROM 旅游线路)
GROUP BY 关联ID, 统计月份;
```
- 每月运行脚本更新财务账目表，确保数据准确。



---

###  (8) 针对每个导游构建用户查询视图和相关信息查询

#### 目标
为每位导游创建SQL视图和查询语句，以便他们能够方便地访问与自己相关的个性化信息，例如分配的旅游团、客户、合同和业绩统计。这确保导游只能访问与其职责相关的数据，避免接触无关或敏感信息。

#### 为每位导游构建用户查询视图

##### 1. 视图：导游个人信息
该视图提供导游的基本信息，包括导游号、姓名、资格等级和所属分公司信息。

```sql
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
```

- **用途**：允许导游查看自己的个人信息和所属分公司详情。
- **访问方式**：导游可通过自己的`导游ID`查询该视图。

**示例查询**（针对特定导游）：
```sql
SELECT * FROM 导游个人信息视图 WHERE 导游ID = 'G001';
```

##### 2. 视图：导游旅游团
该视图列出分配给导游的所有旅游团，包括旅游团详情和相关线路信息。

```sql
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
```

- **用途**：帮助导游查看自己负责的旅游团，包括线路的地点、景点、时间等。
- **访问方式**：导游通过`导游ID`过滤自己的旅游团。

**示例查询**：
```sql
SELECT * FROM 导游旅游团视图 WHERE 导游ID = 'G001' AND 旅游日期 >= '2024-01-01';
```

##### 3. 视图：导游客户列表
该视图显示导游负责的旅游团中的客户信息。

```sql
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
```

- **用途**：允许导游查看每个旅游团的客户列表，便于管理团队。
- **访问方式**：通过`导游ID`和`旅游团ID`查询。

**示例查询**：
```sql
SELECT * FROM 导游客户视图 WHERE 导游ID = 'G001' AND 旅游团ID = 'TG001';
```

##### 4. 视图：导游合同信息
该视图提供导游负责的旅游合同信息。

```sql
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
```

- **用途**：导游可以查看自己负责的合同详情，包括费用、服务等级和保险信息。
- **访问方式**：通过`导游ID`过滤。

**示例查询**：
```sql
SELECT * FROM 导游合同视图 WHERE 导游ID = 'G001' AND 旅游时间 BETWEEN '2024-06-01' AND '2024-12-31';
```

##### 5. 视图：导游业绩统计
该视图汇总导游的月度业绩（基于财务账目）。

```sql
CREATE VIEW 导游业绩视图 AS
SELECT
    关联ID AS 导游ID,
    统计月份,
    SUM(金额) AS 业绩金额
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 导游号 FROM 员工)
GROUP BY 关联ID, 统计月份;
```

- **用途**：导游可以查看自己的月度业绩，了解收入贡献。
- **访问方式**：通过`导游ID`查询。

**示例查询**：
```sql
SELECT * FROM 导游业绩视图 WHERE 导游ID = 'G001' AND 统计月份 = '2024-06-01';
```

#### 相关信息查询

以下是一些导游可能常用的查询语句，基于上述视图：

1. **查询未来一个月的旅游团安排**：
```sql
SELECT 旅游团ID, 线路ID, 地点, 景点, 旅游日期, 服务等级
FROM 导游旅游团视图
WHERE 导游ID = 'G001' 
AND 旅游日期 BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH);
```

2. **查询某旅游团的客户和合同信息**：
```sql
SELECT g.旅游团ID, g.客户ID, g.客户姓名, c.合同ID, c.费用, c.保险信息
FROM 导游客户视图 g
JOIN 旅游信息 ti ON g.客户ID = ti.客户ID
JOIN 导游合同视图 c ON ti.合同ID = c.合同ID
WHERE g.导游ID = 'G001' AND g.旅游团ID = 'TG001';
```

3. **查询年度业绩汇总**：
```sql
SELECT 统计月份, 业绩金额
FROM 导游业绩视图
WHERE 导游ID = 'G001' AND YEAR(统计月份) = 2024
ORDER BY 统计月份;
```

#### 权限控制
为确保数据安全，建议为每位导游分配数据库用户账户，并限制视图访问权限：
```sql
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
```



---

###  (9) 用JDBC实现数据库互联，并构造优化后的操作界面



#### 技术栈

- **数据库**：MySQL（数据库名为`tourism_db`）。

- **编程语言**：Java 22（使用JavaFX 22构建GUI）。

- **JDBC驱动**：MySQL Connector/J 8.0.30。

- **依赖管理**：Maven（简化库管理）。

- **开发环境**：IntelliJ IDEA 。

- **附加库**：
    - JavaFX（UI框架）。
    - Apache Commons CSV（导出CSV）。
    - BCrypt（密码加密）。

- **操作系统**：跨平台（Windows/Linux/Mac）。




#### 项目结构

项目名称：`Tour-guide-query-interface`

```
Tour-guide-query-interface/
├── pom.xml                            # Maven配置文件，定义依赖和构建规则
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yyc/TourGuideQueryInterface/
│   │   │       ├── TourGuideQueryInterface.java  # 主程序入口（JavaFX Application）
│   │   │       ├── DatabaseConnection.java # 数据库连接类（HikariCP）
│   │   │       ├── GuideModel.java        # 数据模型（Model，处理数据库操作）
│   │   │       ├── LoginController.java   # 登录界面控制器
│   │   │       ├── MainController.java    # 主界面控制器
│   │   ├── resources/
│   │       ├── com/yyc/TourGuideQueryInterface/
│   │       │   ├── login.fxml             # 登录界面FXML文件
│   │       │   ├── main.fxml              # 主界面FXML文件
│   │       ├── light-theme.css            # 浅色主题CSS样式
│   │       ├── dark-theme.css             # 深色主题CSS样式
│   │       └── application.properties     # 配置文件（数据库连接参数）
│   └── module-info.java
└── README.md      # 项目说明文档
```



#### 实现步骤

##### 1. 配置JDBC和JavaFX环境
- **Maven依赖**（`pom.xml`）：
  ```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  
      <!-- 基本项目信息 -->
      <modelVersion>4.0.0</modelVersion>
      <groupId>com.example</groupId>
      <artifactId>Tour-guide-query-interface</artifactId>
      <version>1.0-SNAPSHOT</version>
  
      <!-- 依赖管理 -->
      <dependencies>
          <!-- MySQL JDBC 驱动 -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>8.0.30</version>
          </dependency>
          <!-- JavaFX -->
          <dependency>
              <groupId>org.openjfx</groupId>
              <artifactId>javafx-controls</artifactId>
              <version>22</version>
          </dependency>
          <dependency>
              <groupId>org.openjfx</groupId>
              <artifactId>javafx-fxml</artifactId>
              <version>22</version>
          </dependency>
          <!-- Apache Commons CSV -->
          <dependency>
              <groupId>org.apache.commons</groupId>
              <artifactId>commons-csv</artifactId>
              <version>1.10.0</version>
          </dependency>
          <!-- BCrypt 密码加密 -->
          <dependency>
              <groupId>org.mindrot</groupId>
              <artifactId>jbcrypt</artifactId>
              <version>0.4</version>
          </dependency>
          <dependency>
              <groupId>com.zaxxer</groupId>
              <artifactId>HikariCP</artifactId>
              <version>5.1.0</version>
          </dependency>
      </dependencies>
  
      <!-- 构建配置 -->
      <build>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.8.1</version>
                  <configuration>
                      <source>9</source>
                      <target>9</target>
                  </configuration>
              </plugin>
          </plugins>
      </build>
  </project>
  
  ```
- **JavaFX配置**：

    - 在`module-info.java`中添加：
      ```java
      module travel.system {
          requires javafx.controls;
          requires javafx.fxml;
          requires java.sql;
          requires org.apache.commons.csv;
          requires jbcrypt;
          requires com.zaxxer.hikari;
          requires mysql.connector.java;
          opens com.yyc.TourGuideQueryInterface to javafx.fxml;
          exports com.yyc.TourGuideQueryInterface;
      }
      ```
- **数据库准备**：
    - 确保`tourism_db`数据库已创建，包含所有表和视图。
    - 创建导游用户账户并分配视图权限。
    - 添加用户认证表：
      ```sql
      CREATE TABLE 导游用户 (
          导游ID VARCHAR(10) PRIMARY KEY,
          密码哈希 VARCHAR(60) NOT NULL,
          FOREIGN KEY (导游ID) REFERENCES 员工(导游号)
      );
      -- 示例用户（密码使用BCrypt加密）
      INSERT INTO 导游用户 (导游ID, 密码哈希) 
      VALUES ('G001', '$2a$10$r2jl/Km0t0piq5rlRl6SNe0RBDRqP0DXYfG7Hs8wJ9ZWSR0KFS7D6');
      ```



##### 2. JDBC连接
DatabaseConnection.java

```java
package com.yyc.TourGuideQueryInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tourism_db";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // 根据用户名和密码创建数据库连接
    public static Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(JDBC_URL, username, password);
    }
}

```



##### 3. MVC架构设计

将程序分为模型（Model）、视图（View）和控制器（Controller），提高代码可维护性。

- **Model**：处理数据逻辑（如查询数据库）。
- **View**：JavaFX FXML文件定义界面。
- **Controller**：处理用户交互和业务逻辑。



##### 4. JavaFX界面设计

使用FXML定义界面布局，包含以下组件：
- **登录界面**：导游ID输入框、密码输入框、登录按钮、主题切换下拉框。
- **主界面**：
    - 侧边栏：导航按钮（个人信息、旅游团、客户、合同、业绩）。
    - 工具栏：日期范围选择器、导出CSV按钮。
    - 内容区域：表格显示查询结果，支持分页。
    - 状态栏：显示错误或成功提示。

**FXML文件**（`main.fxml`）：
```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<BorderPane fx:id="mainPane"
            xmlns:fx="http://javafx.com/fxml"
            fx:controller="com.yyc.TourGuideQueryInterface.MainController"
            style="-fx-background-color: #f4f4f4;">

    <!-- 顶部工具栏 -->
    <top>
        <HBox spacing="15"
              style="-fx-padding: 10; -fx-background-color: #ffffff; -fx-border-color: #ddd;">
            <Label text="日期范围：" style="-fx-font-weight: bold;" />
            <DatePicker fx:id="startDatePicker" />
            <Label text="至" style="-fx-font-weight: bold;" />
            <DatePicker fx:id="endDatePicker" />
            <Button fx:id="exportButton"
                    text="导出CSV"
                    style="-fx-background-color: #28a745; -fx-text-fill: white; -fx-cursor: hand;"
                    onAction="#exportToCSV" />

            <ComboBox fx:id="themeCombo"
                      promptText="选择主题"
                      style="-fx-pref-width: 120;" />
        </HBox>
    </top>

    <!-- 左侧导航 -->
    <left>
        <VBox spacing="10"
              style="-fx-padding: 15; -fx-background-color: #ffffff; -fx-border-color: #ddd;">
            <Label text="导航菜单" style="-fx-font-size: 16px; -fx-font-weight: bold;" />
            <Separator />

            <Button fx:id="profileButton"
                    text="👤 个人信息"
                    prefWidth="200"
                    onAction="#queryProfile"
                    style="-fx-background-color: #007bff; -fx-text-fill: white; -fx-cursor: hand;" />

            <Button fx:id="tourGroupButton"
                    text="&#x1F6C5; 旅游团"
                    prefWidth="200"
                    onAction="#queryTourGroups"
                    style="-fx-background-color: #17a2b8; -fx-text-fill: white; -fx-cursor: hand;" />

            <Button fx:id="clientButton"
                    text="👥 客户"
                    prefWidth="200"
                    onAction="#queryClients"
                    style="-fx-background-color: #ffc107; -fx-text-fill: black; -fx-cursor: hand;" />

            <Button fx:id="clientConsumptionButton"
                    text="&#x1F4B0; 客户消费"
                    prefWidth="200"
                    onAction="#queryClientConsumption"
                    style="-fx-background-color: #20c997; -fx-text-fill: white; -fx-cursor: hand;" />


            <Button fx:id="contractButton"
                    text="&#x1F4DA; 合同"
                    prefWidth="200"
                    onAction="#queryContracts"
                    style="-fx-background-color: #dc3545; -fx-text-fill: white; -fx-cursor: hand;" />

            <Button fx:id="performanceButton"
                    text="📈 个人业绩"
                    prefWidth="200"
                    onAction="#queryPerformance"
                    style="-fx-background-color: #6610f2; -fx-text-fill: white; -fx-cursor: hand;" />

            <Button fx:id="routeIncomeButton"
                    text="💰 线路业绩"
                    prefWidth="200"
                    onAction="#queryRouteIncome"
                    style="-fx-background-color: #6f42c1; -fx-text-fill: white; -fx-cursor: hand;" />

            <Button fx:id="branchPerformanceButton"
                    text="🏢 分公司业绩"
                    prefWidth="200"
                    onAction="#queryBranchPerformance"
                    style="-fx-background-color: #fd7e14; -fx-text-fill: white; -fx-cursor: hand;" />
        </VBox>
    </left>

    <!-- 中心内容 -->
    <center>
        <VBox spacing="10" style="-fx-padding: 10;">
            <HBox spacing="10">
                <Label text="状态：" />
                <Label fx:id="statusLabel" style="-fx-text-fill: #007bff;" />
            </HBox>

            <TableView fx:id="resultTable" style="-fx-table-cell-border-color: #ccc;">
                <placeholder>
                    <Label text="请点击左侧按钮进行查询" style="-fx-font-style: italic; -fx-text-fill: gray;" />
                </placeholder>
            </TableView>
        </VBox>
    </center>

    <!-- 底部状态栏 -->
    <bottom>
        <HBox alignment="CENTER"
              style="-fx-background-color: #e9ecef; -fx-padding: 10;">
            <Label text="© 2025 导游查询系统" style="-fx-text-fill: #6c757d;" />
        </HBox>
    </bottom>
</BorderPane>
```

**登录界面FXML**（`login.fxml`）：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<BorderPane xmlns:fx="http://javafx.com/fxml"
            fx:controller="com.yyc.TourGuideQueryInterface.LoginController"
            style="-fx-background-color: #f9f9f9;"
            prefWidth="400"
            prefHeight="350">

    <!-- 中央内容 -->
    <center>
        <VBox alignment="CENTER"
              spacing="20"
              style="-fx-padding: 40px;">
            <!-- Logo or Title -->
            <Label text="&#x1F3E0; 导游查询系统"
                   style="-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;" />

            <!-- ID 输入 -->
            <HBox spacing="1" alignment="CENTER">
                <Label text="&#x1F464; 导游ID:" style="-fx-min-width: 80px;" />
                <TextField fx:id="guideIdField" promptText="输入导游ID" style="-fx-pref-width: 200px;" />
            </HBox>

            <!-- 密码输入 -->
            <HBox spacing="1" alignment="CENTER">
                <Label text="&#x1F510; 密码:" style="-fx-min-width: 80px;" />
                <PasswordField fx:id="passwordField" promptText="输入密码" style="-fx-pref-width: 200px;" />
            </HBox>

            <!-- 登录按钮 -->
            <Button fx:id="loginButton"
                    text="登录"
                    onAction="#handleLogin"
                    style="-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 100px;" />

            <!-- 错误提示 -->
            <Label fx:id="errorLabel"
                   style="-fx-text-fill: red; -fx-font-size: 14px;"
                   prefHeight="20" />
        </VBox>
    </center>

    <!-- 底部状态栏 -->
    <bottom>
        <HBox alignment="CENTER"
              style="-fx-background-color: #e9ecef; -fx-padding: 10;">
            <Label text="© 2025 导游查询系统" style="-fx-text-fill: #6c757d;" />
        </HBox>
    </bottom>

</BorderPane>

```



##### 5. Java代码

以下是主要类的实现，采用MVC模式。

**模型（GuideModel）**：

```java
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

```

**登录控制器（LoginController）**：

```java
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
```

**主控制器（MainController）**：
```java
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
```

**主程序（TourGuideQueryInterface）**：

```java
package com.yyc.TourGuideQueryInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class TourGuideQueryInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/yyc/TourGuideQueryInterface/login.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("导游查询系统 - 登录");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/Guide.png"))));

        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

**CSS样式**（`light-theme.css` 和 `dark-theme.css` 示例）：
```css
/* light-theme.css */
.root {
    -fx-background-color: #f4f4f4;
    -fx-font-family: "Arial";
}
.button {
    -fx-background-color: #4CAF50;
    -fx-text-fill: white;
}
.table-view {
    -fx-background-color: white;
}

/* dark-theme.css */
.root {
    -fx-background-color: #333;
    -fx-font-family: "Arial";
}
.button {
    -fx-background-color: #666;
    -fx-text-fill: white;
}
.table-view {
    -fx-background-color: #444;
}
```



##### 6. 运行与调试
- **运行环境**：
    - JDK 22，JavaFX 22，MySQL 8.0。
    - 配置Maven项目，下载所有依赖。
    - 确保`tourism_db`数据库运行，导游用户表已初始化。
- **调试**：
    - 验证JDBC连接（检查URL、用户名、密码）。
    - 测试登录认证（确保BCrypt密码匹配）。
    - 检查查询结果（验证视图数据和日期过滤）。
    - 测试CSV导出（确保文件生成且内容正确）。
- **错误处理**：
    - 捕获SQL异常、IO异常，显示用户友好的提示。
    - 处理空结果集，显示“无数据”提示。

##### 7. 部署
- **打包**：
    - 使用Maven打包为可执行JAR，包含JavaFX和所有依赖。
    - 示例命令：`mvn clean package`.
    - 生成`guide-query-system.jar`。
- **分发**：
    - 提供安装指南，要求用户安装JRE 17和MySQL。
    - 包含数据库初始化脚本（建表、视图、用户）。
- **跨平台测试**：
    - 在Windows、Linux、Mac上测试，确保JavaFX兼容性。


---



### **考核要求执行建议**
1. **实验报告**：
    - 使用统一模板，包含封面、目录、实验目标、设计过程、结果分析、总结。
    - 描述每个成员分工（如数据建模、SQL实现、界面开发）。
2. **小组分工**：
    - 组长：协调任务，撰写综合报告。
    - 组员：分担建模、SQL、数据生成、界面开发等。
3. **阶段报告**：
    - 每次实验后提交，记录完成的任务（如DFD绘制、E-R图设计）。
    - 下次实验课签名提交。
4. **成果展示**：
    - 准备PPT，展示系统功能（查询、统计、界面）。
    - 每人参与答辩，阐述个人贡献。
5. **系统调试**：
    - 确保SQL脚本无语法错误，数据插入成功。
    - 界面功能可操作，查询结果正确。

---

### **总结**
本实验涵盖数据库设计全流程，从需求分析到实现优化。通过完成DFD、E-R图、关系模型、SQL实现和财务统计，学生可掌握数据库理论与实践。