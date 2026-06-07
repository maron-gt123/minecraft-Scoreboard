package net.maron_gt123.mcscoreboard.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class Placeholders {

    private Placeholders() {}

    public static String apply(String text, Player player) {

        text = text.replace("&", "§");

        Location location = player.getLocation();

        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("%player%", player.getName());
        placeholders.put("%world%", player.getWorld().getName());
        placeholders.put("%x%", String.valueOf(location.getBlockX()));
        placeholders.put("%y%", String.valueOf(location.getBlockY()));
        placeholders.put("%z%", String.valueOf(location.getBlockZ()));
        placeholders.put("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
        placeholders.put("%ping%", String.valueOf(player.getPing()));
        placeholders.put("%biome%", player.getLocation().getBlock().getBiome().name());
        placeholders.put("%max_players%", String.valueOf(Bukkit.getMaxPlayers()));
        placeholders.put("%tps%", String.format("%.2f", Bukkit.getTPS()[0]));

        long worldTime = player.getWorld().getTime();
        long hours = (worldTime / 1000 + 6) % 24;
        long minutes = (worldTime % 1000) * 60 / 1000;
        String time = String.format("%02d:%02d", hours, minutes);
        long day = player.getWorld().getFullTime() / 24000;
        placeholders.put("%time%", time);
        placeholders.put("%day%", String.valueOf(day));

        Runtime runtime = Runtime.getRuntime();
        long used = runtime.totalMemory() - runtime.freeMemory();
        long max = runtime.maxMemory();
        long usedMB = used / 1024 / 1024;
        long maxMB = max / 1024 / 1024;
        placeholders.put("%ram%",
                usedMB + "MB / " + maxMB + "MB");

        for (var entry : placeholders.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }

        return text;
    }
}