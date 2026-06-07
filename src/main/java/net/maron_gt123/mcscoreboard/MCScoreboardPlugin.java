package net.maron_gt123.mcscoreboard;

import net.maron_gt123.mcscoreboard.listener.PlayerJoinListener;
import net.maron_gt123.mcscoreboard.scoreboard.SidebarManager;
import net.maron_gt123.mcscoreboard.scoreboard.BoardManager;
import net.maron_gt123.mcscoreboard.command.MSBCommand;
import net.maron_gt123.mcscoreboard.task.ScoreboardTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCScoreboardPlugin extends JavaPlugin {

    private SidebarManager sidebarManager;
    private BoardManager boardManager;

    public BoardManager getBoardManager() {
        return boardManager;
    }

    @Override
    public void onEnable() {

        saveDefaultConfig();

        saveResource("boards/default.yml", false);

        this.boardManager = new BoardManager(this);
        this.sidebarManager = new SidebarManager(this);

        String defaultBoard = getConfig().getString("default-board", "default");
        boardManager.setActiveBoard(defaultBoard);

        getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(this),
                this
        );

        new ScoreboardTask(sidebarManager, this)
                .runTaskTimer(this, 20L, 20L);

        MSBCommand cmd = new MSBCommand(this);
        getCommand("msb").setExecutor(cmd);
        getCommand("msb").setTabCompleter(cmd);

        getLogger().info("MCScoreboard Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("MCScoreboard Disabled");
    }

    public SidebarManager getSidebarManager() {
        return sidebarManager;
    }
}
