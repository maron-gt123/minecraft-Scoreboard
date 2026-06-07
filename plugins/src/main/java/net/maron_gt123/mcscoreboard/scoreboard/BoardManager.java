package net.maron_gt123.mcscoreboard.scoreboard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BoardManager {

    private final JavaPlugin plugin;

    private final Map<String, FileConfiguration> boards = new HashMap<>();
    private String activeBoard = "default";

    public BoardManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadBoards();
    }

    public void loadBoards() {

        File folder = new File(plugin.getDataFolder(), "boards");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        boards.clear();

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {

            if (!file.getName().endsWith(".yml")) continue;

            String name = file.getName().replace(".yml", "");

            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            boards.put(name, config);
        }
    }

    public FileConfiguration getActiveBoard() {
        return boards.get(activeBoard);
    }

    public FileConfiguration getBoard(String name) {
        return boards.get(name);
    }

    public void setActiveBoard(String name) {
        if (boards.containsKey(name)) {
            activeBoard = name;
        }
    }

    public String getActiveBoardName() {
        return activeBoard;
    }

    public Set<String> getBoardNames() {
        return boards.keySet();
    }
}