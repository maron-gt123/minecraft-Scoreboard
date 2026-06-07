package net.maron_gt123.mcscoreboard.scoreboard;

import net.maron_gt123.mcscoreboard.MCScoreboardPlugin;
import net.maron_gt123.mcscoreboard.placeholder.Placeholders;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SidebarUpdater {
    private final MCScoreboardPlugin plugin;

    public SidebarUpdater(MCScoreboardPlugin plugin) {
        this.plugin = plugin;
    }

    public void update(Player player, Scoreboard board) {

        Objective objective = board.getObjective("sidebar");

        if (objective == null) return;

        board.getEntries().forEach(board::resetScores);

        FileConfiguration boardConfig =
                plugin.getBoardManager().getActiveBoard();

        if (boardConfig == null) return;

        objective.setDisplayName(
                plugin.getConfig()
                        .getString("server-name", "&6&lMC Server")
                        .replace("&", "§")
        );

        var section = boardConfig.getConfigurationSection("lines");

        if (section == null) return;

        var keys = section.getKeys(false)
                .stream()
                .map(Integer::parseInt)
                .sorted()
                .toList();

        int score = keys.size();

        for (Integer key : keys) {

            String line = section.getString(String.valueOf(key), "");

            line = Placeholders.apply(line, player);

            objective.getScore(line).setScore(score--);
        }
    }
}
