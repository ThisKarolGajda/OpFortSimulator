package me.opkarol.opfortsimulator.fort;

import me.opkarol.opfortsimulator.SimpleWorldLocation;
import me.opkarol.opfortsimulator.buildings.BuildingsList;
import me.opkarol.opfortsimulator.fort.roles.FortRolesSet;
import me.opkarol.opfortsimulator.fort.roles.IFortRole;
import me.opkarol.opfortsimulator.players.FortPlayersList;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public interface IFort extends Serializable {
    String getFortName();

    UUID getFortUUID();

    SimpleWorldLocation getSpawnLocation();

    BuildingsList getBuildingsList();

    FortPlayersList getFortPlayersList();

    FortRangeBorders getFortRangeBorder();

    FortRolesSet getFortRolesSet();

    default Optional<IFortRole> getFortRoleForPlayer(UUID uuid) {
        throw new RuntimeException("Method not implemented!");
    }

    default void spawnFortBorder(Player player) {
        throw new RuntimeException("Method not implemented!");
    }

    default void expandFortBorder(int x, int z) {
        throw new RuntimeException("Method not implemented!");
    }
}