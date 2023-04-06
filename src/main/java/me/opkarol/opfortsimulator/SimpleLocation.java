package me.opkarol.opfortsimulator;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public record SimpleLocation(double x, double y, double z) implements Serializable {

    @Contract("_ -> new")
    public static @NotNull SimpleLocation of(@NotNull Location location) {
        return new SimpleLocation(location.getX(), location.getY(), location.getZ());
    }

    @Contract("_ -> new")
    public static @NotNull SimpleLocation of(@NotNull Player player) {
        return of(player.getLocation());
    }

    @Override
    public String toString() {
        return "SimpleLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}