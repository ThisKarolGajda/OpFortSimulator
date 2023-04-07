package me.opkarol.opfortsimulator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public record SimpleWorldLocation(double x, double y, double z, String world) implements Serializable {

    @Contract("_ -> new")
    public static @NotNull SimpleWorldLocation of(@NotNull Location location) {
        return new SimpleWorldLocation(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
    }

    @Contract("_ -> new")
    public static @NotNull SimpleWorldLocation of(@NotNull Player player) {
        return of(player.getLocation());
    }

    @Override
    public String toString() {
        return "SimpleWorldLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", world='" + world + '\'' +
                '}';
    }

    public World getWorld() {
        return Bukkit.getWorld(world);
    }
}
