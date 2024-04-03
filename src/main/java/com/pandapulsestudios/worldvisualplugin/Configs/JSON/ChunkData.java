package com.pandapulsestudios.worldvisualplugin.Configs.JSON;

import com.pandapulsestudios.pulseconfig.APIS.JSONAPI;
import com.pandapulsestudios.pulseconfig.Interfaces.IgnoreSave;
import com.pandapulsestudios.pulseconfig.Interfaces.JSON.PulseJson;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableArrayList;
import com.pandapulsestudios.pulsecore.FileSystem.DirAPI;
import com.pandapulsestudios.worldvisualplugin.WorldVisualPlugin;
import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

public class ChunkData implements PulseJson {
    @Override
    public String documentID() { return String.format("%s/%s", dataName, chunkName); }

    @Override
    public JavaPlugin mainClass() { return WorldVisualPlugin.Instance; }

    @Override
    public boolean useSubFolder() { return true; }

    @IgnoreSave
    private String dataName;
    @IgnoreSave
    private String chunkName;
    public SaveableArrayList<BlockData> blockDataArray = new SaveableArrayList<>(BlockData.class);

    public ChunkData(String dataName, Chunk chunk, int minY, int maxY){
        this.dataName = dataName;
        chunkName = String.format("%d%d", chunk.getX(), chunk.getZ());

        for(var x = 0; x < 16; x++){
            for(var z = 0; z < 16; z++){
                for(var y = minY; y < maxY; y++){
                    var block = chunk.getBlock(x, y, z);
                    blockDataArray.arrayList.add(new BlockData(block));
                }
            }
        }

        var configPath = JSONAPI.ReturnConfigPath(this);
        DirAPI.Create(String.format("%s/%s", configPath, dataName));
    }
}
