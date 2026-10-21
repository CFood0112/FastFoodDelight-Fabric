# 本地依赖（不提交 Git）

## Farmer's Delight [Fabric] 端口

- 项目：`[Deprecated] Farmer's Delight [Fabric]`
- Modrinth 项目 ID：`4EakbH8e`
- Slug：`farmers-delight-fabric`
- 版本：`0.1.3`
- 文件：`farmers-delight-fabric-1.17.1-0.1.3.jar`
- 大小：`1,128,140` 字节
- SHA-1：`acd64cc454f696843c5c533fdfb690d1cc7e5528`（Modrinth 官方）
- 下载地址：<https://cdn.modrinth.com/data/4EakbH8e/versions/0.1.3/farmers-delight-fabric-1.17.1-0.1.3.jar>

放置路径：

```
fabric-port/libs/farmers-delight-fabric-1.17.1-0.1.3.jar
```

`build.gradle` 通过 `modImplementation files(...)` 引用它。jar 是二进制、不提交 Git；
换机器/重新克隆时需要按上面地址下载并校验 SHA-1。

> 注意：`0.1.3` 是端口版本号，不是 Farmer's Delight 官方版本；端口基于 FD 0.4.3。
> 游戏内 mod id 是 `farmersdelight`（不是项目 slug）。
