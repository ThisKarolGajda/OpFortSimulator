package me.opkarol.opfortsimulator.worldguard;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.opkarol.opfortsimulator.OpFortSimulator;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class WorldGuardRegion {
    public static @NotNull ProtectedRegion expandRegion(@NotNull ProtectedRegion region, int expandX, int expandY, int expandZ) {
        BlockVector3 min = region.getMinimumPoint();
        BlockVector3 max = region.getMaximumPoint();

        // Expand the region in each direction by the specified amount
        BlockVector3 newMin = BlockVector3.at(min.getBlockX() - expandX, min.getBlockY() - expandY, min.getBlockZ() - expandZ);
        BlockVector3 newMax = BlockVector3.at(max.getBlockX() + expandX, max.getBlockY() + expandY, max.getBlockZ() + expandZ);

        // Create a new region with the expanded bounds
        ProtectedRegion newRegion = new ProtectedCuboidRegion(region.getId(), newMin, newMax);
        newRegion.copyFrom(region);
        return newRegion;
    }


    public static void replaceRegion(@NotNull String regionId, ProtectedRegion newRegion, World world) {
        WorldGuardAPI worldGuardAPI = OpFortSimulator.getInstance().getWorldGuardAPI();
        RegionManager regionManager = worldGuardAPI.getRegionManager(world);

        // Remove the old region from the region manager
        regionManager.removeRegion(regionId);

        // Add the new region to the region manager
        regionManager.addRegion(newRegion);

        // Save the changes to the region manager
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void expandAndReplaceRegion(ProtectedRegion oldRegion, int expandX, int expandY, int expandZ, World world) {
        ProtectedRegion newRegion = expandRegion(oldRegion, expandX, expandY, expandZ);
        replaceRegion(oldRegion.getId(), newRegion, world);
    }

    public static void expandAndReplaceRegion(String oldRegionId, int expandX, int expandY, int expandZ, World world) {
        WorldGuardAPI worldGuardAPI = OpFortSimulator.getInstance().getWorldGuardAPI();
        expandAndReplaceRegion(worldGuardAPI.getRegionManager(world).getRegion(oldRegionId), expandX, expandY, expandZ, world);
    }
}