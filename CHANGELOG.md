# Changelog

All versions target **Minecraft 1.17.1 / Fabric**. Both editions use the same mod id (`fastfooddelight_fabric`) and must not be installed together.

Version scheme: the Faithful Edition keeps the original mod's `1.2.1` version with a `+mc1.17.1` suffix; the Enhanced Edition uses `1.3.0+mc1.17.1` to distinguish its source and artifact. The `+mc1.17.1` suffix identifies the Minecraft version, not a separate mod version.

## 1.3.0+mc1.17.1 — Enhanced Edition (release 2026-10-19; rebuilt 2026-10-21)

**Artifact:** `fastfooddelight-fabric-1.17.1-1.3.0-enhanced.jar` — 114,024 bytes
**SHA-256:** `036A366060AF5437FC1994F5FCFA8141E8BAF67084F334E701F7BE1777784A7C`

Changes compared with Faithful Edition `1.2.1+mc1.17.1`:

- Added a crafting recipe for the checkout machine: 4 iron ingots, 1 glass, 1 redstone, and 3 paper (`IGI` / `IRI` / `PPP`).
- Added the correct `minecraft:mineable/pickaxe` block tag for the checkout machine. A pickaxe is now the correct tool in survival; the original broken tag file is kept unchanged for reference.
- Fixed the street pools of the four non-plains village houses so they use their own biome's streets pool instead of the plains pool.
- Added one `fast_food_waiter` to each of the four non-plains village houses (desert, savanna, snowy, taiga). The original leatherworker is kept.
- Apple Sauce cooking now requires and returns a bowl.
- Chicken Wrap now uses 11 nutrition / 0.8 saturation with Resistance I for 15 seconds (previously 10 / 0.8 with no effect).
- Removed the ineffective top-level `count` field from the cheese recipe. The output is still 8 cheese per milk bucket.
- Added a random FastFood Delight food loot table to all five village shop chests.

Everything else is identical to Faithful Edition: 26 items, 30 waiter trades, five village houses, the checkout machine workstation, the four Farmer's Delight cooking pot recipes, and the Simplified Chinese language file (`zh_cn`).

## 1.2.1+mc1.17.1 — Faithful Edition (release 2026-10-19; rebuilt 2026-10-21)

**Artifact:** `fastfooddelight-fabric-1.17.1-1.2.1-faithful.jar` — 110,485 bytes
**SHA-256:** `BB99C084117461E96D4D7527ABC6966BD10CDB6767CEDD7AA396B945F7CA7637`

Initial Fabric 1.17.1 release, based on the original FastFood Delight `1.19-1.2.1` source.

Included:

- Forge 1.19 → Fabric 1.17.1 port of all 26 items, the checkout machine block, the waiter villager profession, 30 trades, and five village houses.
- 1.17.1 data and resource conversions: registry namespace, recipes, tags, models, blockstates, and structure files.
- A custom smelting serializer that preserves the original 1 milk bucket → 8 cheese output.
- Migration fixes: waiter profession texture, 1.17.1 trade-title translation key, correct food/drink stack sizes, and per-world village injection.
- Original `en_us`, `en_gb`, and `fr_fr` language files, plus a new `zh_cn` language file built from the source tree.
- Original values and behavior are preserved, including known original quirks and bugs. See the project README for the list.

## Release packaging

The downloadable archives are:

- `fastfooddelight-fabric-1.17.1-1.2.1-faithful.zip` — 88,378 bytes, SHA-256 `67828C8001DA6A9B482EED23EE9CE2DD672437A10A19FC6649D532A17058EC6A`
- `fastfooddelight-fabric-1.17.1-1.3.0-enhanced.zip` — 90,945 bytes, SHA-256 `3C61BC7041C345FF207E1C60E2255CDE7AAE482F8A85711CCCC7033DBB287CCA`

Each archive contains the edition JAR, `README.md`, `LICENSES.md`, and `SHA256SUMS.txt`. They do not contain Farmer's Delight, Forge, or Minecraft files.

## Publication rebuild (2026-10-20; license wording updated 2026-10-21)

The artifacts above were rebuilt from the source tree for publication readiness:

- 2026-10-21: rebuilt after the project owner verified the original CurseForge license field as MIT; `LICENSE`/`NOTICE` and release `LICENSES.md` now record that verification.
- Both JARs now ship `META-INF/LICENSE` (MIT text plus the original author's copyright line and the port copyright) and `META-INF/NOTICE` (attribution and third-party notices).
- The Faithful Edition's `zh_cn.json` is now part of the real source tree and normal build; it is no longer injected during packaging.
- Both editions use the same original in-game icon (`icon.png`).
- Release archives are built with stable entry order and timestamps, and both editions were built twice from clean state to verify reproducible contents.
- Release file names use the `fastfooddelight-fabric-1.17.1-<mod-version>-<edition>` form.
- The pre-remediation artifacts (2026-10-19) are preserved only under `releases/archive/pre-remediation-2026-10-19/` for traceability; do not distribute them.

If the JARs are rebuilt again, update this changelog, `SHA256SUMS.txt`, and the `.zip.sha256` sidecars together.

## Compatibility

| Edition | Minecraft | Fabric Loader (tested) | Fabric API (tested) | Farmer's Delight [Fabric] |
|---|---|---|---|---|
| Faithful `1.2.1+mc1.17.1` | 1.17.1 | 0.12.12 | 0.46.1+1.17 | 0.1.3 |
| Enhanced `1.3.0+mc1.17.1` | 1.17.1 | 0.12.12 | 0.46.1+1.17 | 0.1.3 |

The JAR metadata declares lower minimums (Fabric Loader `>=0.11.6`, Fabric API `>=0.40.1+1.17`), but older combinations are not verified.
