package com.pandapulsestudios.worldvisualplugin.Runnables;

import com.pandapulsestudios.pulseconfig.APIS.JSONAPI;
import com.pandapulsestudios.worldvisualplugin.Configs.JSON.ChunkData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class GenerateRunnable extends BukkitRunnable {

    private final String dataName;
    private final Location location;
    private final int radiusXZ;
    private final int minY;
    private final int maxY;
    private int x;
    private int z;

    public GenerateRunnable(Location location, String dataName, int radiusXZ){
        this.dataName = dataName;
        this.location = location;
        this.radiusXZ = radiusXZ;
        this.minY = location.getWorld().getMinHeight();
        this.maxY = location.getWorld().getMaxHeight();
        this.x = this.location.getChunk().getX() - this.radiusXZ;
        this.z = this.location.getChunk().getZ() - this.radiusXZ;
    }

    public GenerateRunnable(Location location, String dataName, int radiusXZ, int radiusY){
        this.dataName = dataName;
        this.location = location;
        this.radiusXZ = radiusXZ;
        this.minY = Math.max(location.getWorld().getMinHeight(), (int) location.getY() - radiusY);
        this.maxY = Math.min(location.getWorld().getMaxHeight(), (int) location.getY() + radiusY);
    }

    @Override
    public void run() {
        var nextChunk = location.getWorld().getChunkAt(x, z);
        Bukkit.broadcastMessage(String.format("Saving chunk data at {%d, %d}", x, z));
        var chunkData = new ChunkData(dataName, nextChunk, minY, maxY);
        JSONAPI.Save(chunkData, false);

        z += 1;
        if(z >= location.getChunk().getZ() + radiusXZ){
            z = location.getChunk().getZ() - radiusXZ;
            x += 1;
            if(x >= location.getChunk().getX() + radiusXZ){
                cancel();
            }
        }
    }
}
