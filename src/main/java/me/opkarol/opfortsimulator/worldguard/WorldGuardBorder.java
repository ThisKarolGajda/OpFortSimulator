package me.opkarol.opfortsimulator.worldguard;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.opkarol.opc.api.tools.runnable.OpTimerRunnable;
import me.opkarol.opfortsimulator.OpFortSimulator;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WorldGuardBorder {

    public static final double BLOCK_INCREMENT = 1.5;
    public static final int BLOCK_HEIGHT_INCREMENT = 30;
    public static final int PLAYER_VIEW_DISTANCE = 75;

    public static void spawnBorderParticles(@NotNull Player player, String regionName) {
        spawnBorderParticles(player, regionName, 1);
    }

    public static void spawnBorderParticles(@NotNull Player player, String regionName, int particleCount) {
        World world = player.getWorld();
        ProtectedRegion region = OpFortSimulator.getInstance().getWorldGuardAPI()
                .getRegionManager(world)
                .getRegion(regionName);

        if (region == null) {
            return;
        }

        double minX = region.getMinimumPoint().getX();
        double maxX = region.getMaximumPoint().getX();
        double minZ = region.getMinimumPoint().getZ();
        double maxZ = region.getMaximumPoint().getZ();
        int minY = region.getMinimumPoint().getBlockY();
        int maxY = region.getMaximumPoint().getBlockY();

        new OpTimerRunnable().runTaskTimesDownAsynchronously((r, i) -> {
                for (double x = minX; x <= maxX; x += BLOCK_INCREMENT) {
                    spawnParticles(player, x, minY, maxY, minZ, maxZ, particleCount);
                    spawnParticles(player, x, minY, maxY, maxX, minZ, particleCount);
                }
                for (double z = minZ; z <= maxZ; z += BLOCK_INCREMENT) {
                    spawnParticles(player, minX, minY, maxY, z, z, particleCount);
                    spawnParticles(player, maxX, minY, maxY, z, z, particleCount);
                }
                for (double y = minY; y <= maxY; y += BLOCK_INCREMENT * 2) {
                    spawnParticle(player, minX, y, minZ, Particle.GLOW, 3);
                    spawnParticle(player, minX, y, maxZ, Particle.GLOW, 3);
                    spawnParticle(player, maxX, y, minZ, Particle.GLOW, 3);
                    spawnParticle(player, maxX, y, maxZ, Particle.GLOW, 3);
                }
            }, 10);
    }

    private static void spawnParticles(Player player, double x, int minY, int maxY, double z1, double z2, int particleCount) {
        int count = Math.max(1, Math.round((maxY - minY) / 30.0f * particleCount));
        for (double y = minY; y <= maxY; y += BLOCK_HEIGHT_INCREMENT) {
            for (int i = 0; i < count; i++) {
                double z = z1 + (z2 - z1);
                spawnParticle(player, x, y, z, Particle.GLOW);
            }
        }
    }

    private static void spawnParticle(@NotNull Player player, double x, double y, double z, Particle particle, int count) {
        spawnParticle(player, x, y, z, particle, count, PLAYER_VIEW_DISTANCE);
    }

    private static void spawnParticle(@NotNull Player player, double x, double y, double z, Particle particle, int count, int playerViewDistance) {
        Location loc = new Location(player.getWorld(), x, y, z);
        if (player.getLocation().distance(loc) <= playerViewDistance) {
            player.spawnParticle(particle, loc, count);
        }
    }

    private static void spawnParticle(@NotNull Player player, double x, double y, double z, Particle particle) {
        spawnParticle(player, x, y, z, particle, 1);
    }
}