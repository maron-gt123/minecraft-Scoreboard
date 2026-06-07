package net.maron_gt123.mcscoreboard.command;

import net.maron_gt123.mcscoreboard.MCScoreboardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MSBCommand implements CommandExecutor, TabCompleter {

    private final MCScoreboardPlugin plugin;

    public MSBCommand(MCScoreboardPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> handleReload(sender);
            case "create" -> handleCreate(sender);
            case "remove" -> handleRemove(sender);
            case "boards" -> handleBoards(sender);
            case "set" -> handleSet(sender, args);
            case "help" -> sendHelp(sender);
            default -> sender.sendMessage("§cUnknown subcommand");
        }

        return true;
    }

    private void handleReload(CommandSender sender) {
        plugin.reloadConfig();

        for (Player p : Bukkit.getOnlinePlayers()) {
            plugin.getSidebarManager().remove(p);
            plugin.getSidebarManager().create(p);
        }

        sender.sendMessage("§a[MSB] reloaded");
    }

    private void handleCreate(CommandSender sender) {
        if (sender instanceof Player p) {
            plugin.getSidebarManager().create(p);
            sender.sendMessage("§a[MSB] created");
        } else {
            sender.sendMessage("§cThis command is player only");
        }
    }

    private void handleRemove(CommandSender sender) {
        if (sender instanceof Player p) {
            plugin.getSidebarManager().remove(p);
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            sender.sendMessage("§c[MSB] removed");
        }
    }

    private void handleBoards(CommandSender sender) {

        sender.sendMessage("§6==== Boards ====");

        plugin.getBoardManager().getBoardNames()
                .forEach(name -> sender.sendMessage("§e- " + name));
    }

    private void handleSet(CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /msb set <board>");
            return;
        }

        String name = args[1];

        plugin.getBoardManager().setActiveBoard(name);

        sender.sendMessage("§a[MSB] set board: " + name);

        for (Player p : Bukkit.getOnlinePlayers()) {
            plugin.getSidebarManager().remove(p);
            plugin.getSidebarManager().create(p);
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§6==== MSB Commands ====");
        sender.sendMessage("§e/msb reload §7- config reload");
        sender.sendMessage("§e/msb create §7- show scoreboard");
        sender.sendMessage("§e/msb remove §7- hide scoreboard");
        sender.sendMessage("§e/msb boards §7- list boards");
        sender.sendMessage("§e/msb set <board> §7- switch board");
        sender.sendMessage("§e/msb help §7- help");
    }

    // =========================
    // TAB COMPLETION（予測変換）
    // =========================
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            result.add("reload");
            result.add("create");
            result.add("remove");
            result.add("boards");
            result.add("set");
            result.add("help");
            return result;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            return new ArrayList<>(plugin.getBoardManager().getBoardNames());
        }

        return result;
    }
}