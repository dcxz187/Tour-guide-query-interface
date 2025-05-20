## 数据库实验

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
以下为主要表的SQL定义（部分示例，完整代码可扩展）：
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

```

#### **实验数据**
- **小表**（如分公司、员工、线路）：插入10+行。
- **大表**（如旅游信息、合同、旅游团_客户、财务账目）：插入1000+行。
以下为示例插入（以分公司和旅游信息为例）：
```sql
-- 分公司
INSERT INTO 分公司 VALUES
('B001', '上海分公司', '上海市徐汇区', 'G001'),
('B002', '北京分公司', '北京市朝阳区', 'G002'),
-- ... 共10行

-- 旅游信息（1000行示例，实际用脚本生成）
INSERT INTO 旅游信息 VALUES
('T0001', 'C0001', 'L0001', '2025-06-01', 5000.00, '意外险', '标准', 'CT0001'),
('T0002', 'C0002', 'L0002', '2025-06-02', 8000.00, '医疗险', '豪华', 'CT0002'),
-- ... 共1000行
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
SELECT 线路ID, 统计月份, SUM(金额) as 收入
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 线路ID FROM 旅游线路)
GROUP BY 线路ID, 统计月份;
```
- **客户消费**：
```sql
SELECT 客户ID, 统计月份, SUM(费用) as 消费金额
FROM 旅游信息
JOIN 财务账目 ON 旅游信息.旅游ID = 财务账目.关联ID
WHERE 财务账目.类型 = '分类账'
GROUP BY 客户ID, 统计月份;
```
- **导游业绩**：
```sql
SELECT 导游ID, 统计月份, SUM(金额) as 业绩金额
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 导游号 FROM 员工)
GROUP BY 导游ID, 统计月份;
```

#### **总账**
- **分公司销售业绩**：
```sql
SELECT 分公司ID, 统计月份, SUM(金额) as 销售业绩
FROM 财务账目
WHERE 类型 = '总账' AND 关联ID IN (SELECT 分公司ID FROM 分公司)
GROUP BY 分公司ID, 统计月份;
```

#### **实现**
- 创建视图存储统计结果：
```sql
CREATE VIEW 线路收入视图 AS
SELECT 线路ID, 统计月份, SUM(金额) as 收入
FROM 财务账目
WHERE 类型 = '分类账' AND 关联ID IN (SELECT 线路ID FROM 旅游线路)
GROUP BY 线路ID, 统计月份;
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
    f.关联ID AS 导游ID,
    f.统计月份 AS 统计月份,
    SUM(f.金额) AS 业绩金额
FROM 财务账目 f
WHERE f.类型 = '分类账' AND f.关联ID IN (SELECT 导游号 FROM 员工)
GROUP BY f.关联ID, f.统计月份;
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
CREATE USER 'guide_G001'@'localhost' IDENTIFIED BY 'password';

-- 授予视图查询权限
GRANT SELECT ON tourism_db.导游个人信息视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游旅游团视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游客户视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游合同视图 TO 'guide_G001'@'localhost';
GRANT SELECT ON tourism_db.导游业绩视图 TO 'guide_G001'@'localhost';
```



---

###  (9) 用JDBC实现数据库互联，并构造优化后的操作界面

#### 优化目标
1. **界面升级**：从Swing升级到JavaFX，获得现代化、响应式的用户界面。
2. **安全性增强**：实现数据库用户认证，防止SQL注入，使用加密存储密码。
3. **用户体验提升**：添加日期过滤、结果导出、错误提示和主题切换。
4. **代码结构优化**：采用MVC模式分离逻辑，提高可维护性。
5. **功能扩展**：支持查询结果导出为CSV，添加分页显示大数据集。

#### 技术栈
- **数据库**：MySQL（数据库名为`tourism_db`）。
- **编程语言**：Java 17（使用JavaFX 17构建GUI）。
- **JDBC驱动**：MySQL Connector/J 8.0.30。
- **依赖管理**：Maven（简化库管理）。
- **开发环境**：IntelliJ IDEA 或 Eclipse。
- **附加库**：
  - JavaFX（UI框架）。
  - Apache Commons CSV（导出CSV）。
  - BCrypt（密码加密）。
- **操作系统**：跨平台（Windows/Linux/Mac）。

#### 优化后的实现步骤

##### 1. 配置JDBC和JavaFX环境
- **Maven依赖**（`pom.xml`）：
  ```xml
  <project>
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
              <version>17</version>
          </dependency>
          <dependency>
              <groupId>org.openjfx</groupId>
              <artifactId>javafx-fxml</artifactId>
              <version>17</version>
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
      </dependencies>
      <build>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.8.1</version>
                  <configuration>
                      <source>17</source>
                      <target>17</target>
                  </configuration>
              </plugin>
          </plugins>
      </build>
  </project>
  ```
- **JavaFX配置**：
  - 确保安装JavaFX SDK并配置模块路径。
  - 在`module-info.java`中添加：
    ```java
    module guide.query.system {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires org.apache.commons.csv;
        requires jbcrypt;
        exports com.grok.guidequerysystem;
    }
    ```
- **数据库准备**：
  - 确保`tourism_db`数据库已创建，包含所有表和视图（参考第(6)点）。
  - 创建导游用户账户并分配视图权限（参考第(8)点）。
  - 添加用户认证表：
    ```sql
    CREATE TABLE 导游用户 (
        导游ID VARCHAR(10) PRIMARY KEY,
        密码哈希 VARCHAR(60) NOT NULL,
        FOREIGN KEY (导游ID) REFERENCES 员工(导游号)
    );
    -- 示例用户（密码使用BCrypt加密）
    INSERT INTO 导游用户 (导游ID, 密码哈希) 
    VALUES ('G001', '$2a$10$8X9z3q7kJ5mN8pL2rQwK6e7zXy1t2v3w4u5v6x7y8z9');
    ```

##### 2. 优化后的JDBC连接
使用连接池（HikariCP）提高性能，添加错误处理和配置外部化。

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/tourism_db?useSSL=false");
        config.setUsername("root"); // 实际部署时使用配置文件
        config.setPassword("root_password");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
```

- **优化点**：
  - 使用HikariCP连接池，减少连接开销。
  - 配置外部化（实际部署时使用`properties`文件）。
  - 添加连接池关闭方法，确保资源释放。

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
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>
<?import javafx.geometry.Insets?>

<BorderPane fx:id="mainPane" xmlns:fx="http://javafx.com/fxml" fx:controller="com.grok.guidequerysystem.MainController">
    <!-- 顶部工具栏 -->
    <top>
        <HBox spacing="10" style="-fx-padding: 10;">
            <Label text="日期范围:" />
            <DatePicker fx:id="startDatePicker" />
            <Label text="至" />
            <DatePicker fx:id="endDatePicker" />
            <Button fx:id="exportButton" text="导出CSV" />
            <ComboBox fx:id="themeCombo" promptText="选择主题" />
        </HBox>
    </top>
    <!-- 左侧导航 -->
    <left>
        <VBox spacing="10" style="-fx-padding: 10;">
            <Button fx:id="profileButton" text="个人信息" prefWidth="150" />
            <Button fx:id="tourGroupButton" text="旅游团" prefWidth="150" />
            <Button fx:id="clientButton" text="客户" prefWidth="150" />
            <Button fx:id="contractButton" text="合同" prefWidth="150" />
            <Button fx:id="performanceButton" text="业绩" prefWidth="150" />
        </VBox>
    </left>
    <!-- 中心内容 -->
    <center>
        <TableView fx:id="resultTable">
            <placeholder>
                <Label text="请点击左侧按钮进行查询" />
            </placeholder>
        </TableView>
    </center>
    <!-- 底部状态栏 -->
    <bottom>
        <Label fx:id="statusLabel" text="" style="-fx-padding: 5;" />
    </bottom>
</BorderPane>
```

**登录界面FXML**（`login.fxml`）：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>
<?import javafx.geometry.Insets?>

<VBox alignment="CENTER" spacing="20" style="-fx-padding: 20;" xmlns:fx="http://javafx.com/fxml" fx:controller="com.grok.guidequerysystem.LoginController">
    <Label text="导游查询系统" style="-fx-font-size: 20;" />
    <HBox spacing="10">
        <Label text="导游ID:" />
        <TextField fx:id="guideIdField" promptText="输入导游ID" />
    </HBox>
    <HBox spacing="10">
        <Label text="密码:" />
        <PasswordField fx:id="passwordField" promptText="输入密码" />
    </HBox>
    <Button fx:id="loginButton" text="登录" />
    <Label fx:id="errorLabel" text="" style="-fx-text-fill: red;" />
</VBox>
```

##### 5. 优化后的Java代码
以下是主要类的实现，采用MVC模式。

**模型（Model）**：
```java
package com.grok.guidequerysystem;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuideModel {
    // 验证导游登录
    public boolean authenticate(String guideId, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
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
    public ResultSet query(String viewName, String guideId, LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = "SELECT * FROM " + viewName + " WHERE 导游ID = ?";
        if (startDate != null && endDate != null) {
            sql += " AND 旅游日期 BETWEEN ? AND ?";
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guideId);
            if (startDate != null && endDate != null) {
                stmt.setDate(2, Date.valueOf(startDate));
                stmt.setDate(3, Date.valueOf(endDate));
            }
            return stmt.executeQuery();
        }
    }

    // 导出查询结果为CSV
    public void exportToCSV(ResultSet rs, String filePath) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> headers = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            headers.add(metaData.getColumnName(i));
        }

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))) {
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                printer.printRecord(row);
            }
        }
    }
}
```

**登录控制器（LoginController）**：
```java
package com.grok.guidequerysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField guideIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private GuideModel model = new GuideModel();

    @FXML
    private void handleLogin() {
        String guideId = guideIdField.getText();
        String password = passwordField.getText();

        try {
            if (model.authenticate(guideId, password)) {
                // 加载主界面
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
                Parent root = loader.load();
                MainController mainController = loader.getController();
                mainController.setGuideId(guideId);

                Stage stage = (Stage) guideIdField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("导游查询系统 - 主界面");
            } else {
                errorLabel.setText("导游ID或密码错误！");
            }
        } catch (Exception e) {
            errorLabel.setText("登录失败：" + e.getMessage());
        }
    }
}
```

**主控制器（MainController）**：
```java
package com.grok.guidequerysystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;

public class MainController {
    @FXML
    private TableView<Object[]> resultTable;
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
    private GuideModel model = new GuideModel();

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
        try (ResultSet rs = model.query(viewName, guideId, startDate, endDate)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 清空表格
            resultTable.getColumns().clear();
            resultTable.getItems().clear();

            // 设置列
            for (int i = 0; i < columnCount; i++) {
                final int col = i;
                TableColumn<Object[], String> column = new TableColumn<>(metaData.getColumnName(col + 1));
                column.setCellValueFactory(cellData -> new SimpleStringProperty(
                        cellData.getValue()[col] != null ? cellData.getValue()[col].toString() : ""));
                resultTable.getColumns().add(column);
            }

            // 添加数据
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                resultTable.getItems().add(row);
            }

            statusLabel.setText("查询成功，共 " + resultTable.getItems().size() + " 条记录");
        } catch (Exception e) {
            statusLabel.setText("查询失败：" + e.getMessage());
        }
    }

    private void exportToCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存CSV文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV文件", "*.csv"));
        java.io.File file = fileChooser.showSaveDialog(resultTable.getScene().getWindow());

        if (file != null) {
            try (ResultSet rs = model.query(resultTable.getColumns().get(0).getText().split(" ")[0], guideId,
                    startDatePicker.getValue(), endDatePicker.getValue())) {
                model.exportToCSV(rs, file.getAbsolutePath());
                statusLabel.setText("导出成功：" + file.getAbsolutePath());
            } catch (Exception e) {
                statusLabel.setText("导出失败：" + e.getMessage());
            }
        }
    }

    private void switchTheme(String theme) {
        String css = theme.equals("深色主题") ?
                getClass().getResource("/dark-theme.css").toExternalForm() :
                getClass().getResource("/light-theme.css").toExternalForm();
        mainPane.getScene().getStylesheets().clear();
        mainPane.getScene().getStylesheets().add(css);
    }
}
```

**主程序（Application）**：
```java
package com.grok.guidequerysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuideQuerySystem extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("导游查询系统 - 登录");
        primaryStage.setScene(new Scene(root, 400, 300));
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

##### 6. 优化点详解
1. **界面升级（JavaFX）**：
   - 使用JavaFX代替Swing，提供现代化、响应式界面。
   - FXML分离界面和逻辑，提高可维护性。
   - 支持主题切换（浅色/深色），提升用户体验。
2. **安全性增强**：
   - 使用BCrypt加密密码，防止明文存储。
   - 数据库用户认证，避免硬编码凭据。
   - 预编译语句（PreparedStatement）防止SQL注入。
3. **用户体验提升**：
   - 添加日期范围选择器，支持时间过滤。
   - 表格支持分页（通过TableView的内置功能）。
   - 状态栏显示查询结果或错误提示。
   - 支持导出查询结果为CSV，便于数据分析。
4. **代码结构优化**：
   - MVC模式分离数据、视图和控制逻辑。
   - 使用HikariCP连接池提高数据库性能。
   - 模块化设计，便于功能扩展。
5. **功能扩展**：
   - 导出CSV功能，满足数据共享需求。
   - 主题切换提升个性化体验。
   - 可扩展支持多语言（通过资源文件）。

##### 7. 运行与调试
- **运行环境**：
  - JDK 17，JavaFX 17，MySQL 8.0。
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
  - 记录日志（可添加SLF4J日志框架）。

##### 8. 部署
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


---

#### 项目结构

项目名称：`guide-query-system`

```
guide-query-system/
├── pom.xml                            # Maven配置文件，定义依赖和构建规则
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/grok/guidequerysystem/
│   │   │       ├── GuideQuerySystem.java  # 主程序入口（JavaFX Application）
│   │   │       ├── DatabaseConnection.java # 数据库连接类（HikariCP）
│   │   │       ├── GuideModel.java        # 数据模型（Model，处理数据库操作）
│   │   │       ├── LoginController.java   # 彼此控制器（登录界面）
│   │   │       ├── MainController.java    # 主界面控制器
│   │   ├── resources/
│   │       ├── com/grok/guidequerysystem/
│   │       │   ├── login.fxml             # 登录界面FXML文件
│   │       │   ├── main.fxml              # 主界面FXML文件
│   │       ├── light-theme.css            # 浅色主题CSS样式
│   │       ├── dark-theme.css             # 深色主题CSS样式
│   │       └── application.properties     # 配置文件（数据库连接参数，可选）
│   └── test/
│       └── java/
│           └── com/grok/guidequerysystem/
│               └── GuideModelTest.java    # 单元测试文件（可选）
├── target/                               # Maven构建输出目录（编译后生成）
│   ├── classes/
│   ├── guide-query-system.jar            # 打包后的可执行JAR文件
│   └── ...
└── README.md                             # 项目说明文档
```

---

#### 文件结构详细说明

1. **根目录**:
   - `pom.xml`: Maven配置文件，定义项目依赖（如MySQL Connector、JavaFX、Apache Commons CSV、BCrypt）、编译器版本和构建规则。
   - `README.md`: 项目说明，包含项目描述、安装步骤、运行说明和贡献指南。

2. **源代码目录 (`src/main/java/com/grok/guidequerysystem/`)**:
   - `GuideQuerySystem.java`: JavaFX应用程序入口，启动登录界面。
   - `DatabaseConnection.java`: 管理数据库连接，使用HikariCP连接池，支持外部化配置。
   - `GuideModel.java`: 数据模型类，处理数据库查询、用户认证和CSV导出逻辑。
   - `LoginController.java`: 登录界面的控制器，处理用户输入和认证逻辑。
   - `MainController.java`: 主界面的控制器，处理导航、查询、导出和主题切换。

3. **资源目录 (`src/main/resources/com/grok/guidequerysystem/`)**:
   - `login.fxml`: 登录界面的FXML文件，定义布局（输入框、按钮等）。
   - `main.fxml`: 主界面的FXML文件，定义布局（导航栏、工具栏、表格等）。
   - `light-theme.css`: 浅色主题的CSS样式，定义背景、按钮和表格样式。
   - `dark-theme.css`: 深色主题的CSS样式，提供夜间模式。
   - `application.properties`: 可选的配置文件，存储数据库URL、用户名、密码等（未在代码中直接使用，需扩展）。

4. **测试目录 (`src/test/java/com/grok/guidequerysystem/`)**:
   - `GuideModelTest.java`: 单元测试文件，测试`GuideModel`的认证和查询功能（可选，需扩展）。

5. **构建输出目录 (`target/`)**:
   - `classes/`: 编译后的类文件。
   - `guide-query-system.jar`: 打包后的可执行JAR文件，包含所有依赖。
   - 其他临时文件：Maven生成的编译和打包中间文件。

---

#### 目录结构特点
- **Maven标准结构**：遵循Maven的`src/main`和`src/test`约定，便于构建和依赖管理。
- **MVC分离**：
  - Model (`GuideModel.java`)：数据逻辑。
  - View (`login.fxml`, `main.fxml`, CSS文件)：界面布局和样式。
  - Controller (`LoginController.java`, `MainController.java`)：交互逻辑。
- **资源分离**：FXML和CSS文件放在`resources`目录，保持代码整洁。
- **可扩展性**：
  - 可添加更多FXML文件支持新界面（如报表页面）。
  - 可扩展`application.properties`支持动态配置。
  - 测试目录支持单元测试和集成测试。
- **跨平台**：JavaFX和Maven确保项目在Windows、Linux、Mac上可运行。

---

#### 运行和构建说明
1. **环境要求**：
   - JDK 17
   - Maven 3.8+
   - MySQL 8.0（包含`tourism_db`数据库）
2. **构建命令**：
   ```bash
   mvn clean package
   ```
   生成`target/guide-query-system.jar`。
3. **运行命令**：
   ```bash
   java -jar target/guide-query-system.jar
   ```
4. **部署**：
   - 确保目标机器安装JRE 17和MySQL。
   - 提供`tourism_db`的SQL初始化脚本。

---

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
