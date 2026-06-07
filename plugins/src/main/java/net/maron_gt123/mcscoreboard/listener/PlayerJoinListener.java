package net.maron_gt123.mcscoreboard.listener;

import net.maron_gt123.mcscoreboard.scoreboard.SidebarManager;
import net.maron_gt123.mcscoreboard.MCScoreboardPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MCScoreboardPlugin plugin;

    public PlayerJoinListener(MCScoreboardPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String defaultBoard =
                plugin.getConfig().getString("default-board", "default");

        plugin.getBoardManager().setActiveBoard(defaultBoard);

        plugin.getSidebarManager().create(player);
    }
}
