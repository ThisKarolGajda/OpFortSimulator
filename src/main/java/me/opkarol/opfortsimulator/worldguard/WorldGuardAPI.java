package me.opkarol.opfortsimulator.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public record WorldGuardAPI(RegionContainer regionContainer) {

    public RegionManager getRegionManager(World world) {
        return regionContainer().get(BukkitAdapter.adapt(world));
    }

    public void addRegion(World world, ProtectedRegion region) {
        RegionManager regionManager = getRegionManager(world);
        regionManager.addRegion(region);
    }

    public void addRegion(World world, String id, Location location1, Location location2) {
        addRegion(world, new ProtectedCuboidRegion(id, fromLocation(location1), fromLocation(location2)));
    }

    public void addRegionWithOwner(@NotNull Player player, String id, Location location1, Location location2) {
        addRegion(player.getWorld(), new ProtectedCuboidRegion(id, fromLocation(location1), fromLocation(location2)));
        getRegionManager(player.getWorld()).getRegion(id).getOwners().addPlayer(player.getUniqueId());
    }

    public void addRegion(World world, String id, BlockVector3 location1, BlockVector3 location2) {
        addRegion(world, new ProtectedCuboidRegion(id, location1, location2));
    }

    public BlockVector3 fromLocation(@NotNull Location location) {
        return BlockVector3.at(location.getX(), location.getY(), location.getZ());
    }

    public BlockVector3 addFromLocation(Location location, double x, double y, double z) {
        return fromLocation(location).add(BlockVector3.at(x, y, z));
    }

    public ApplicableRegionSet getApplicableRegionSet(Location location) {
        RegionQuery query = regionContainer().createQuery();
        return query.getApplicableRegions(BukkitAdapter.adapt(location));
    }

    public Future<Boolean> isRegionNotOccupied(@NotNull Location location1, @NotNull Location location2) {
        int xMin = Math.min(location1.getBlockX(), location2.getBlockX());
        int yMin = Math.min(location1.getBlockY(), location2.getBlockY());
        int zMin = Math.min(location1.getBlockZ(), location2.getBlockZ());
        int xMax = Math.max(location1.getBlockX(), location2.getBlockX());
        int yMax = Math.max(location1.getBlockY(), location2.getBlockY());
        int zMax = Math.max(location1.getBlockZ(), location2.getBlockZ());

        return CompletableFuture.supplyAsync(() -> {
            for (int x = xMin; x <= xMax; x++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int z = zMin; z <= zMax; z++) {
                        Location location = new Location(location1.getWorld(), x, y, z);
                        ApplicableRegionSet regions = getApplicableRegionSet(location);
                        if (regions.size() > 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        });
    }

    public void removeRegion(World world, String id) {
        getRegionManager(world).removeRegion(id);
    }
}
