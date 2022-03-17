package com.chillaxmc.customnpcbridge.Utils;

import com.chillaxmc.customnpcbridge.CustomNPCBridge;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseJson {

    public static Object getTeamsData(String uuid, CustomNPCBridge plugin){
        try {
            File worldContainer = Bukkit.getServer().getWorldContainer();

            if(worldContainer.exists()){
                String dataRoot = worldContainer.getAbsolutePath() + File.separator + "customnpcs" + File.separator + "playerdata" + File.separator;
                try {

                    String fileOutput = Files.readAllLines(Paths.get((dataRoot + uuid + ".json"))).toString()
                            .replaceAll("([0-9])b|L","$1");

                    JSONArray ja = (JSONArray) new JSONParser().parse(fileOutput);
                    JSONObject jo = (JSONObject) ja.get(0);
                    return jo.get("FactionData");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
