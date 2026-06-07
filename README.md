# Minecraft Scoreboard Overview

---

A lightweight and customizable Minecraft scoreboard plugin for Paper servers.

## Features

* Dynamic sidebar scoreboard
* Automatic scoreboard updates
* TPS display
* Online player count display
* World information display
* Coordinate display
* Date and time display
* Ping display

---

## Requirements

* Paper 1.21 or higher (recommended)

---

## Commands

| Command            | Description                                                   |
| ------------------ | ------------------------------------------------------------- |
| `/msb help`        | Display help                                                  |
| `/msb reload`      | Reload configuration and recreate scoreboards for all players |
| `/msb create`      | Display your scoreboard                                       |
| `/msb remove`      | Remove your scoreboard                                        |
| `/msb boards`      | Display a list of available boards                            |
| `/msb set <board>` | Switch the active board                                       |

---

## Placeholders

MCScoreboard supports the following placeholders within scoreboards.

### Player Information

| Placeholder | Description |
| ----------- | ----------- |
| `%player%`  | Player name |
| `%ping%`    | Player ping |

### Location Information

| Placeholder | Description        |
| ----------- | ------------------ |
| `%world%`   | Current world name |
| `%x%`       | X coordinate       |
| `%y%`       | Y coordinate       |
| `%z%`       | Z coordinate       |
| `%biome%`   | Current biome name |

### Server Information

| Placeholder     | Description                  |
| --------------- | ---------------------------- |
| `%online%`      | Online player count          |
| `%max_players%` | Maximum player count         |
| `%tps%`         | Server TPS                   |
| `%ram%`         | Used memory / Maximum memory |

### World Information

| Placeholder | Description          |
| ----------- | -------------------- |
| `%time%`    | Minecraft world time |
| `%day%`     | Elapsed day count    |

---

## Configuration Files

MCScoreboard uses the following configuration files.

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

Specify the scoreboard title and the default board.

```yaml
server-name: "&6&lMC Server"

# Default board
default-board: "default"
```

### boards/*.yml

Each YAML file inside the `boards/` folder defines a single scoreboard.

You can create multiple boards and switch between them using commands.

```bash
boards/
├── survival.yml
├── lobby.yml
└── event.yml
```

Sample:

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

### Color Codes

MCScoreboard supports Minecraft color codes.

```yaml
title: "&6Server Info"
```

| Code | Color        |
| ---- | ------------ |
| `&0` | Black        |
| `&1` | Dark Blue    |
| `&2` | Dark Green   |
| `&3` | Dark Aqua    |
| `&4` | Dark Red     |
| `&5` | Dark Purple  |
| `&6` | Gold         |
| `&7` | Gray         |
| `&8` | Dark Gray    |
| `&9` | Blue         |
| `&a` | Green        |
| `&b` | Aqua         |
| `&c` | Red          |
| `&d` | Light Purple |
| `&e` | Yellow       |
| `&f` | White        |

---

## License

This project is released under the MIT License.
