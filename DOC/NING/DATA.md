### 用户信息

1. 用户帐号名
2. 用户密码
3. 用户邮箱
4. 用户电话
5. 用户性别
6. 用户年龄
7. 用户简介
8. 用户qq识别
9. 用户头像
11. 用户收件地址（建议设置为外键，放在地址表里）
12. 用户所在地（地址同上）

---

### 商品数据：

1. 出售人（需包含出售人的id）
1. 出售者规定的交易地点（建议改为交易地点范围，文字描述形式）
1. 商品名字
1. 种类
1. 价格
1. 商品描述
1. 商品图片（3-5张图片，或打包成一个文件）

### 商品分类（这个还没好）

- `类别设置：个位数为一级类别，十位数为二级类别`
- `比如说上衣的分类为21，2代表上衣，1代表所属分类为闲置衣物`
- `分类尽量控制在十个以内`

- 闲置衣物 = 1
    - 包 = 11
    - 上衣 = 21
    - 裤子 = 31
    - 鞋子 = 41
    - 其他 = 51
- 数码设备 = 2
    - 手机 = 12
    - 平板 = 22
    - 电脑 = 32
    - 其他 = 42
- 闲置书籍 = 3
    - 教材（要求可以根据`学校、年级、专业`信息查询所需）
    - 其他书籍
- 。。。

---

订单数据：

1. 购买者信息
1. 商品信息
1. 购买者交易地点（改为约定地点）
1. 交易时间（改为预定时间）
1. 备注

---

委托信息

1. 委托概述
1. 委托者信息
1. 委托详细描述和注意事项
1. 发布时间（添加项）
1. 截止时间（添加项）
1. 0-3张图片（添加项）

---

委托订单

1. 接受委托者信息
1. 委托号（委托信息id？）
1. 接受时间
1. 备注