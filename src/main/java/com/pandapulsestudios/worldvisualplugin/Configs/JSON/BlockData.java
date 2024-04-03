package com.pandapulsestudios.worldvisualplugin.Configs.JSON;

import com.pandapulsestudios.pulseconfig.Interfaces.PulseClass;
import org.bukkit.block.Block;

public class BlockData implements PulseClass {

    public int x;
    public int y;
    public int z;
    public String blockType;

    public BlockData(Block block){
        x = block.getX();
        y = block.getY();
        z = block.getZ();
        blockType = block.getType().name();
    }

}
