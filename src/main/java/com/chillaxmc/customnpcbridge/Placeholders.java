package com.chillaxmc.customnpcbridge;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Placeholders extends PlaceholderExpansion {
    private final CustomNPCBridge plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public Placeholders(CustomNPCBridge plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "CustomNPCBridge";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TheJbone";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.toLowerCase(Locale.ROOT).contains("getf")){
            String teamId = params.substring(params.lastIndexOf('f') + 1);
            return String.valueOf(plugin.getUserReputation(player.getUniqueId().toString(), Integer.parseInt(teamId)));
        }
        return null; // Placeholder is unknown by the Expansion
    }

}