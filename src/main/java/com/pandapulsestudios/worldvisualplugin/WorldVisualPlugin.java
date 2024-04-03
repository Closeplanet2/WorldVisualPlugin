package com.pandapulsestudios.worldvisualplugin;

import com.pandapulsestudios.pulsecommands.PlayerCommand;
import com.pandapulsestudios.pulsecommands.PulseCommands;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class WorldVisualPlugin extends JavaPlugin {

    public static WorldVisualPlugin Instance;
    public List<BukkitRunnable> generationTasks = new ArrayList<>();

    @Override
    public void onEnable() {
        Instance = this;

        PulseCommands.RegisterRaw(this);
        ClassAPI.RegisterClasses(this);
    }
}
