# FastFood Delight: Fabric Port

**FastFood Delight 的非官方 Minecraft 1.17.1 Fabric 移植版（原作者：Akaneo）。**

本项目把原版 Forge 模组 FastFood Delight 的快餐菜单、服务员村民、收银机和村庄快餐店带到 Minecraft 1.17.1 的 Fabric 平台。这是一个独立的社区项目，**与原作者、Farmer's Delight 团队及 Mojang 均无隶属关系**，也未获得其背书或支持。

> **两个版本只能装一个。** Faithful Edition 与 Enhanced Edition 使用同一个 mod id（`fastfooddelight_fabric`），同时安装会导致游戏无法正常启动。

## 项目概览

- **原版模组：** Minecraft Forge 1.19 版 FastFood Delight，作者 **Akaneo**（CurseForge 用户名 `akaneo2`）。
  - CurseForge：<https://www.curseforge.com/minecraft/mc-mods/fastfood-delight>
  - GitHub：<https://github.com/akaneoMT/FastFoodDelight>
- **本项目：** 非官方 Fabric 1.17.1 移植，基于原版 `1.19-1.2.1` 源码；原版后续的 1.2.3 / 1.2.4 未纳入移植。
- **发行版本：** Faithful Edition（忠实迁移）与 Enhanced Edition（在忠实迁移基础上加入经确认的修复和有意改动）。
- **许可证：** 原版与本移植均为 MIT。详见 [LICENSE](LICENSE) 与 [NOTICE](NOTICE)。

本项目只面向 **Minecraft 1.17.1 + Fabric**，不做多版本支持；没有新增 GUI、结账交易系统或自定义实体，也没有在下列内容之外扩充玩法。

## 发行版本

### Faithful Edition — `1.2.1+mc1.17.1`

Faithful Edition 的目标是保留原版的内容、数值和行为，只进行让模组能在 Minecraft 1.17.1 / Fabric 上运行所必需的适配，包括：

- 1.17.1 数据与资源转换（注册命名空间、配方、标签、模型、blockstate、结构文件）；
- 自定义熔炼序列化器，在 1.17.1 上保留原版“1 桶牛奶 → 8 个奶酪”的产量；
- 迁移修复：服务员职业贴图、1.17.1 交易界面职业名键、正确的食物/饮料堆叠上限、按世界分别注入村庄结构；
- 新增简体中文（`zh_cn`），原版 `en_us`、`en_gb`、`fr_fr` 保留（这是新增内容，不是运行所必需）。

原版的怪数值与已知缺陷按项目决定原样保留，详见[已知问题](#已知问题与更新日志)。

### Enhanced Edition — `1.3.0+mc1.17.1`

Enhanced Edition 基于同一套移植代码，加入下表所列的已批准修复与有意改动。它不是另一个模组：基础内容完全一致；除 Chicken Wrap 的食物数值外，差异都在数据与资源层面。如果你希望收银机可以合成、生存模式能正常挖掘、每个村庄都有服务员、非平原村庄的街道连接按生物群系生成，并想要少量额外村庄战利品，可以选这个版本。

### 两个版本的主要差异

| 项目 | Faithful Edition | Enhanced Edition |
|---|---|---|
| 收银机配方 | 无配方（与原版一致） | 可合成：4 铁锭 + 1 玻璃 + 1 红石 + 3 纸 |
| 收银机挖掘 | 保留原版错误标签（生存模式挖掘慢、不掉落） | 使用正确的 `minecraft:mineable/pickaxe` 标签（镐为正确工具） |
| 非平原村庄街道 | 保留原版池（全部指向平原街道） | 改为对应生物群系的街道池 |
| 非平原村庄服务员 | 无（只有皮匠） | 每栋非平原快餐店新增 1 名服务员，原皮匠保留 |
| Apple Sauce 烹饪 | 不要求/不返还碗（原版行为） | 要求并返还碗 |
| Chicken Wrap | 10 饥饿 / 0.8 饱和度，无效果 | 11 / 0.8，附带抗性 I（15 秒） |
| 村庄箱子 | 空箱（与原版一致） | 随机生成模组食物战利品 |
| 奶酪配方 | 含一个不生效的顶层 `count` 字段 | 删除该字段；产量仍为 1 桶牛奶 → 8 奶酪 |
| 语言 | `en_us`、`en_gb`、`fr_fr`、`zh_cn` | 相同 |

Enhanced Edition 包含 Faithful Edition 的全部物品、配方、交易和结构，并在此基础上加入上述改动。两版共用同一套 Java 代码；唯一有差异的 Java 类是负责食物数值的 `FFFoodValues`，且其中只有 Chicken Wrap 的数值不同。

## 功能内容

### 食物与饮料

- **26 个注册物品：** 24 种食物/饮品（19 种固体食物 + 5 种饮料）、1 个薯条盒容器物品、1 个收银机方块物品。
- **汉堡、三明治与卷饼：** 奶酪汉堡、鸡肉奶酪三明治、法式烤火腿奶酪三明治、猪肉三明治、鳕鱼三明治、鲑鱼三明治、兔肉三明治、奶酪卷饼、鸡肉卷。
- **炸物：** 薯条、胡萝卜薯条、炸洋葱、鸡块；薯条与炸洋葱食用后返还薯条盒。
- **饮料：** 可乐、橙味汽水、柠檬汽水、奶油汽水、冰茶；可随时饮用，给予 15 秒生命恢复 I，返还玻璃瓶，堆叠上限 16。
- **甜点：** 冰淇淋、巧克力冰淇淋、甜浆果冰淇淋、水果冰淇淋、苹果酱；食用后返还碗。
- **奶酪：** 可食用食材（4 饥饿 / 0.4 饱和度）。
- 一个独立的 **FastFood Delight 创造模式标签页**，包含全部 26 个物品。

### 服务员村民与交易

- 自定义职业 **`fast_food_waiter`**，以收银机为工作站。
- **30 条交易**，分 5 个等级（4 / 10 / 5 / 5 / 6），全部用金粒支付；其中 7 条出售 Farmer's Delight 的食物。和原版交易一样，每个等级在交易界面只随机展示其中一部分。
- 无业村民可以自然认领收银机并成为服务员。

### 村庄快餐店

- **5 个结构**（平原、沙漠、热带草原、雪地、针叶林），在服务器启动时注入对应的原版村庄房屋池（权重 25）。
- 每栋建筑包含收银机、村庄风格内饰和箱子。
- Enhanced Edition 中，非平原快餐店还会生成服务员，5 栋建筑的箱子都会随机生成 FastFood Delight 食物。

### 收银机

- 水平朝向方块，碰撞箱高 10 像素，沿用铁块属性。
- 它是服务员的工作站，也是装饰性机器。与原版一致，**没有 GUI，也没有结账交易逻辑**；本移植没有新增这些功能。

### 与 Farmer's Delight 的联动

- 4 个 `farmersdelight:cooking` 烹饪锅配方：薯条、胡萝卜薯条、炸洋葱、苹果酱。
- 部分配方和 7 条服务员交易使用 Farmer's Delight 物品。
- 必须安装 **Farmer's Delight [Fabric]** 依赖（见下文）。

### 语言

- 原版的 `en_us`、`en_gb`、`fr_fr`，以及本项目新增的 `zh_cn`。两个发行 JAR 都包含这四种语言。

## 兼容性与运行要求

| 组件 | Mod 元数据声明的最低版本 | 开发与测试实际使用的版本 |
|---|---|---|
| Minecraft | **1.17.1**（精确匹配） | 1.17.1 |
| Fabric Loader | `>=0.11.6` | 0.12.12 |
| Fabric API | `>=0.40.1+1.17` | 0.46.1+1.17 |
| Farmer's Delight [Fabric] | `>=0.1.3`（mod id `farmersdelight`） | 0.1.3 |
| Java | 16 或更新（Minecraft 1.17.1 的要求） | JDK 16 |

说明：

- 上表右列是已验证的组合。JAR 元数据声明了更低的最低版本，但**更低版本未经测试**；条件允许时请使用 Fabric Loader 0.12.12 + Fabric API 0.46.1+1.17。
- **Farmer's Delight [Fabric] 0.1.3** 是社区制作的独立端口，基于 Farmer's Delight 0.4.3（Modrinth 项目 `farmers-delight-fabric`，MIT），已归档，**不是官方 Farmer's Delight**。官方 Farmer's Delight 没有 Fabric 版本；适用于更高版本 Minecraft 的 Farmer's Delight Refabricated 是另一个项目，请勿混淆。
- 支持客户端与专用服务器；模组没有客户端专属入口。
- 没有 JEI、AppleSkin、CraftTweaker 联动；不承诺与其他模组、其他加载器、其他 Minecraft 版本或原版 Forge 存档兼容。

## 安装方法

1. 安装 **Minecraft 1.17.1** 与 **Fabric Loader**（<https://fabricmc.net/use/installer/>）。
2. 下载适用于 1.17.1 的 **Fabric API**（<https://modrinth.com/mod/fabric-api>）和 **Farmer's Delight [Fabric] 0.1.3**（<https://modrinth.com/mod/farmers-delight-fabric/version/cRnlUfGm>）。
3. 下载**一个** FastFood Delight: Fabric Port JAR：Faithful Edition 或 Enhanced Edition。
4. 把 Fabric API、Farmer's Delight [Fabric] 和所选的 FastFood Delight JAR 放进 Minecraft 的 `mods` 目录。
5. 启动 Fabric 1.17.1 配置。

**不要同时安装两个版本。** 它们共用 mod id `fastfooddelight_fabric`；`mods` 目录里放两份会导致重复 mod 错误。切换版本时请先删除旧 JAR。

当前发行文件：

| 版本 | JAR 文件 |
|---|---|
| Faithful `1.2.1+mc1.17.1` | `fastfooddelight-fabric-1.17.1-1.2.1-faithful.jar` |
| Enhanced `1.3.0+mc1.17.1` | `fastfooddelight-fabric-1.17.1-1.3.0-enhanced.jar` |

SHA-256 校验值见 [CHANGELOG.md](CHANGELOG.md)。

## 已知问题与更新日志

各版本的具体改动见 [CHANGELOG.md](CHANGELOG.md)。

已知问题与有意保留的限制：

- **收银机没有 GUI，也没有结账功能。** 它只是装饰方块和服务员工作站，与原版一致。
- **两个版本中，5 种饮料都没有合成配方**，只能通过服务员交易获得。
- **Faithful Edition 保留原版怪癖与缺陷**，包括：收银机挖掘标签错误（生存模式挖掘慢、不掉落）；非平原村庄没有服务员；非平原结构指向平原街道池；Apple Sauce 烹饪不要求碗；Chicken Wrap 为 10/0.8 且无效果；鸡块沿用薯条数值；1 桶牛奶产出 8 个奶酪。
- **Enhanced Edition：** 部分非平原村庄里，服务员的职业贴图可能被生物群系服装部分遮挡。这是纯视觉问题，不影响交易或 AI。
- 测试期间曾出现一次 Farmer's Delight 交易物品显示异常（`mixed_salad` 显示为 `air`），之后未能复现，目前继续观察。
- 部分客户端视觉检查仍需人工完成（例如 Chicken Wrap 的效果图标、街道连接的细节视觉检查）；这些功能的服务端行为已经验证。
- 模组仅支持 **Minecraft 1.17.1**，无法在 1.18+、1.19+ 或 Forge/NeoForge 上加载。

本项目**不宣称“所有 Bug 已修复”**：Enhanced Edition 只修复了上面列出的项目，多项原版行为是有意保留的。

## 致谢与许可证

- **原版模组：** FastFood Delight，作者 **Akaneo**（CurseForge：`akaneo2`），Minecraft Forge 1.19 版。
  - CurseForge：<https://www.curseforge.com/minecraft/mc-mods/fastfood-delight>
  - GitHub：<https://github.com/akaneoMT/FastFoodDelight>
- **Fabric 移植与修改：** 由 **CFood0112** 完成，以 MIT 许可证发布。
- **许可证文件：** [LICENSE](LICENSE) 为 MIT 许可证全文；[NOTICE](NOTICE) 为来源归属与第三方声明。
- **归属声明：**
  - Original mod: FastFood Delight by Akaneo.
  - Fabric port and modifications: CFood0112.
  - This is an unofficial port and is not affiliated with or endorsed by the original author.

**关于上游许可证文件：** 原版模组在 `mods.toml` 中声明 `license="MIT License"`，其 CurseForge 项目于 2026-10-21 经项目负责人核实为 MIT。上游 GitHub 仓库同时包含一份 Forge MDK 自带的 `LICENSE.txt`（Forge/FML 的 LGPL-2.1 文本，以及 MCP 和第三方说明）；该文件属于 Forge MDK 模板，不是 FastFood Delight 模组代码的授权声明。本移植不复制、不再分发 MDK、Forge、FML、MCP、Mojang 或 Minecraft 的代码。完整说明见 [NOTICE](NOTICE)。

**第三方组件（均为依赖项，不随本项目打包）：**

- **Farmer's Delight**（作者 vectorwing）— MIT；
- **Farmer's Delight [Fabric] 0.1.3**（作者 Zifiv、vectorwing、StevenPlayzz）— MIT，必需依赖，不包含在本项目文件中；
- **Fabric Loader** 与 **Fabric API** — Apache License 2.0，必需依赖，不包含；
- **Minecraft** — Mojang/Microsoft，不包含。

两个版本使用同一套为本项目准备的原创图标素材。CurseForge 项目头像另行准备，不是原项目头像的复制。

本项目为非官方项目，与 Akaneo、vectorwing、Farmer's Delight 团队、Mojang、Microsoft 或 Fabric 项目均无隶属关系，也未获其背书。

## 支持与问题反馈

- 本 Fabric 移植的问题请通过本仓库的 GitHub Issue 反馈。
- **不要**把 Fabric 移植的问题报告给原版 FastFood Delight 作者或 Farmer's Delight 团队；Farmer's Delight [Fabric] 自身的问题请报告给该项目。
- 提交问题时请附上：
  - 发行版本与版本号（Faithful `1.2.1+mc1.17.1` 或 Enhanced `1.3.0+mc1.17.1`）；
  - Minecraft、Fabric Loader、Fabric API、Farmer's Delight [Fabric] 的版本；
  - 其它已安装模组列表；
  - 完整的 `latest.log` 和崩溃报告；
  - 清晰的复现步骤。
- 欢迎功能建议与平衡性意见，但 Faithful Edition 不会采用破坏忠实度的行为改动。

## 截图

![FastFood Delight 创造标签页，包含全部 26 个物品](screenshots/creative-tab.png)

*FastFood Delight 创造标签页，包含全部 26 个物品。*

![生成在村庄中的快餐店](screenshots/village-house.png)

*生成在村庄中的快餐店。*

<!-- 后续可补充：收银机前的服务员村民、收银机近景、食物与饮料摆桌、甜点，以及可选的 Faithful/Enhanced 差异对比。 -->

## 免责声明

本项目是非官方粉丝移植，按“现状”提供，不附带任何明示或暗示的担保。Minecraft 是 Mojang Synergies AB 的商标；FastFood Delight 的著作权归原作者；Farmer's Delight 的著作权归 vectorwing。所有商标与版权归各自权利人所有。
