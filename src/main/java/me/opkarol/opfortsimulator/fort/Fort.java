package me.opkarol.opfortsimulator.fort;

import me.opkarol.opfortsimulator.SimpleWorldLocation;
import me.opkarol.opfortsimulator.buildings.BuildingsList;
import me.opkarol.opfortsimulator.fort.roles.FortRolesSet;
import me.opkarol.opfortsimulator.fort.roles.IFortRole;
import me.opkarol.opfortsimulator.players.FortPlayer;
import me.opkarol.opfortsimulator.players.FortPlayersList;
import me.opkarol.opfortsimulator.worldguard.WorldGuardBorder;
import me.opkarol.opfortsimulator.worldguard.WorldGuardRegion;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public abstract class Fort implements IFort, Serializable {
    private final BuildingsList buildingsList = new BuildingsList();
    private final FortPlayersList fortPlayersList = new FortPlayersList();
    private final FortRangeBorders fortRangeBorders = new FortRangeBorders();
    private final FortRolesSet rolesSet = new FortRolesSet();
    private SimpleWorldLocation spawnLocation;

    @Override
    public BuildingsList getBuildingsList() {
        return buildingsList;
    }

    @Override
    public FortPlayersList getFortPlayersList() {
        return fortPlayersList;
    }

    @Override
    public FortRangeBorders getFortRangeBorder() {
        return fortRangeBorders;
    }

    @Override
    public FortRolesSet getFortRolesSet() {
        return rolesSet;
    }

    @Override
    public SimpleWorldLocation getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(SimpleWorldLocation spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @Override
    public String toString() {
        return "fort-" + getFortUUID();
    }

    @Override
    public Optional<IFortRole> getFortRoleForPlayer(UUID playerUUID) {
        Optional<FortPlayer> optional = getFortPlayersList().getPlayer(playerUUID);
        if (optional.isEmpty()) {
            return Optional.empty();
        }

        FortPlayer fortPlayer = optional.get();
        return getFortRolesSet().getRole(fortPlayer.getPlayerRoleIndex());
    }

    @Override
    public void spawnFortBorder(Player player) {
        WorldGuardBorder.spawnBorderParticles(player, toString());
    }

    @Override
    public void expandFortBorder(int x, int z) {
        WorldGuardRegion.expandAndReplaceRegion(toString(), x * FortRangeBorders.SINGLE_UNIT_LENGTH, 0, z * FortRangeBorders.SINGLE_UNIT_LENGTH, getSpawnLocation().getWorld());
        FortRangeBorders borders = getFortRangeBorder();
        borders.setX(borders.getX() + x);
        borders.setZ(borders.getZ() + z);
    }
}