package com.pandapulsestudios.worldvisualplugin.Commands;

import com.pandapulsestudios.pulsecommands.Enums.TabType;
import com.pandapulsestudios.pulsecommands.Interface.PCCommand;
import com.pandapulsestudios.pulsecommands.Interface.PCMethod;
import com.pandapulsestudios.pulsecommands.Interface.PCSignature;
import com.pandapulsestudios.pulsecommands.Interface.PCTab;
import com.pandapulsestudios.pulsecommands.PlayerCommand;
import com.pandapulsestudios.worldvisualplugin.Runnables.GenerateRunnable;
import com.pandapulsestudios.worldvisualplugin.WorldVisualPlugin;
import org.bukkit.Bukkit;

import java.util.UUID;

@PCCommand
public class WorldVisualCommands extends PlayerCommand {
    public WorldVisualCommands() {
        super("Worldvisual", false, "wv");
    }

    @PCMethod
    @PCSignature("Generate")
    @PCTab(pos = 0, type = TabType.Pure_Data, data = "[DATA NAME]")
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DATA RADIUS XZ]")
    public void GenerateWorld(UUID playerUUID, String dataName, int radiusXZ){
        var player = Bukkit.getPlayer(playerUUID);
        if(player == null) return;
        var genTask = new GenerateRunnable(player.getLocation(), dataName, radiusXZ);
        genTask.runTaskTimer(WorldVisualPlugin.Instance, 0, 1);
        WorldVisualPlugin.Instance.generationTasks.add(genTask);
    }

    @PCMethod
    @PCSignature("Generate")
    @PCTab(pos = 0, type = TabType.Pure_Data, data = "[DATA NAME]")
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DATA RADIUS XZ]")
    @PCTab(pos = 2, type = TabType.Pure_Data, data = "[DATA RADIUS Y]")
    public void GenerateWorld(UUID playerUUID, String dataName, int radiusXZ, int radiusY){
        var player = Bukkit.getPlayer(playerUUID);
        if(player == null) return;
        var genTask = new GenerateRunnable(player.getLocation(), dataName, radiusXZ, radiusY);
        genTask.runTaskTimer(WorldVisualPlugin.Instance, 0, 1);
        WorldVisualPlugin.Instance.generationTasks.add(genTask);
    }
}
