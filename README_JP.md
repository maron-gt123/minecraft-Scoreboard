# Minecraft Scoreboard 概要

---

Paper サーバー向けの軽量かつカスタマイズ可能な Minecraft スコアボードプラグインです。

## 機能

* 動的なサイドバースコアボード
* スコアボードの自動更新
* TPS 表示
* オンラインプレイヤー数表示
* ワールド情報表示
* 座標表示
* 日付・時刻表示
* Ping 表示

---

## 動作要件
* Paper 1.21 以上（推奨）

---

## コマンド

| コマンド | 説明 |
|----------|------|
| `/msb help` | ヘルプを表示 |
| `/msb reload` | 設定を再読み込みし、全プレイヤーのスコアボードを再生成 |
| `/msb create` | 自分のスコアボードを表示 |
| `/msb remove` | 自分のスコアボードを削除 |
| `/msb boards` | 利用可能なボード一覧を表示 |
| `/msb set <board>` | アクティブなボードを切り替え |

---

## プレースホルダー

MCScoreboard では、スコアボード内で以下のプレースホルダーを利用できます。

### プレイヤー情報

| プレースホルダー   | 説明          |
| ---------- | ----------- |
| `%player%` | プレイヤー名      |
| `%ping%`   | プレイヤーの Ping |

### 位置情報

| プレースホルダー  | 説明        |
| --------- | --------- |
| `%world%` | 現在のワールド名  |
| `%x%`     | X座標       |
| `%y%`     | Y座標       |
| `%z%`     | Z座標       |
| `%biome%` | 現在のバイオーム名 |

### サーバー情報

| プレースホルダー        | 説明            |
| --------------- | ------------- |
| `%online%`      | オンラインプレイヤー数   |
| `%max_players%` | 最大プレイヤー数      |
| `%tps%`         | サーバーTPS       |
| `%ram%`         | 使用メモリ / 最大メモリ |

### ワールド情報

| プレースホルダー | 説明               |
| -------- | ---------------- |
| `%time%` | Minecraftワールド内時刻 |
| `%day%`  | 経過日数             |

---

## 設定ファイル
MCScoreboard は以下の設定ファイルを使用します

```bash
plugins/
 └── MCScoreboard/
  ├── config.yml
  └── boards/
   ├── default.yml
   ├── survival.yml
   └── lobby.yml
```
### config.yml
スコアボードのタイトルと初期表示ファイルを指定

```yaml
server-name: "&6&lMC Server"

# 初期で使うボード
default-board: "default"
```
### boards/*.yml
`boards/` フォルダ内の各 YAML ファイルは、1つのスコアボードを定義します。

複数のボードを作成し、コマンドで切り替えることができます。

```bash
boards/
├── survival.yml
├── lobby.yml
└── event.yml
```
サンプル
```yaml
lines:
  1: "&7%time% &8| &eDay %day%"
  2: "&aOnline: &f%online% / %max_players%"
  3: "&7TPS: &f%tps%"
  4: "&cRAM: &f%ram%"
  5: ""
  6: "&bPlayer: &f%player%"
  7: "&7World: &f%world%"
  8: "&aBiome: &f%biome%"
  9: ""
  10: "&7X: &f%x% &7Y: &f%y% &7Z: &f%z%"
  11: "&7Ping: &f%ping%ms"
```

## ライセンス

このプロジェクトは MIT License のもとで公開されています。
