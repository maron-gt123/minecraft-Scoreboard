package net.maron_gt123.mcscoreboard.scoreboard;

import net.maron_gt123.mcscoreboard.MCScoreboardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import io.papermc.paper.scoreboard.numbers.NumberFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SidebarManager {
    private final MCScoreboardPlugin plugin;
    private final Map<UUID, Scoreboard> scoreboards = new HashMap<>();

    public SidebarManager(MCScoreboardPlugin plugin) {
        this.plugin = plugin;
    }

    public void create(Player player) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective(
                "sidebar",
                Criteria.DUMMY,
                plugin.getConfig().getString(
                        "title",
                        "§6§lMC Server"
                ).replace("&", "§")
        );

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.numberFormat(NumberFormat.blank());
        scoreboards.put(player.getUniqueId(), board);

        player.setScoreboard(board);
    }

    public Scoreboard get(Player player) {
        return scoreboards.get(player.getUniqueId());
    }

    public void remove(Player player) {
        scoreboards.remove(player.getUniqueId());
    }

    public Map<UUID, Scoreboard> getScoreboards() {
        return scoreboards;
    }
}
