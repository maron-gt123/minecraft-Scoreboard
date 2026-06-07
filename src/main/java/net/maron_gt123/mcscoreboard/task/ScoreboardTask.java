package net.maron_gt123.mcscoreboard.task;

import net.maron_gt123.mcscoreboard.MCScoreboardPlugin;
import net.maron_gt123.mcscoreboard.scoreboard.SidebarManager;
import net.maron_gt123.mcscoreboard.scoreboard.SidebarUpdater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask extends BukkitRunnable {
    private final SidebarManager sidebarManager;
    private final SidebarUpdater sidebarUpdater;

    public ScoreboardTask(
            SidebarManager sidebarManager,
            MCScoreboardPlugin plugin
    ) {
        this.sidebarManager = sidebarManager;
        this.sidebarUpdater = new SidebarUpdater(plugin);
    }

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            Scoreboard board = sidebarManager.get(player);

            if (board == null) {
                continue;
            }

            sidebarUpdater.update(player, board);
        }
    }
}
