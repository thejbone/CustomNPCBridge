package com.chillaxmc.customnpcbridge;

import com.chillaxmc.customnpcbridge.Utils.ParseJson;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.concurrent.atomic.AtomicLong;

public final class CustomNPCBridge extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Small check to make sure that PlaceholderAPI is installed
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders(this).register();
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().info("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public long getUserReputation(String uuid, int team){

        JSONArray jsonArray;
        AtomicLong returnValue = new AtomicLong(0);
        try{
            jsonArray = (JSONArray) ParseJson.getTeamsData(uuid, this);
            if(jsonArray != null && !jsonArray.isEmpty()){
                jsonArray.stream().filter(a -> (Long) ((JSONObject) a).get("Faction") == team).mapToLong(a -> ((Long) ((JSONObject) a).get("Points"))).forEach(returnValue::set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue.longValue();
    }
}
