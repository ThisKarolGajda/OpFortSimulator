package me.opkarol.opfortsimulator.fort;

import me.opkarol.opfortsimulator.SimpleLocation;
import me.opkarol.opfortsimulator.buildings.BuildingsList;
import me.opkarol.opfortsimulator.fort.roles.FortRolesSet;
import me.opkarol.opfortsimulator.fort.roles.IFortRole;
import me.opkarol.opfortsimulator.players.FortPlayer;
import me.opkarol.opfortsimulator.players.FortPlayersList;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public abstract class Fort implements IFort, Serializable {
    private final BuildingsList buildingsList = new BuildingsList();
    private final FortPlayersList fortPlayersList = new FortPlayersList();
    private final FortRangeBorders fortRangeBorders = new FortRangeBorders();
    private final FortRolesSet rolesSet = new FortRolesSet();
    private SimpleLocation spawnLocation;

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
    public SimpleLocation getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(SimpleLocation spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @Override
    public String toString() {
        return "FORT-" + getFortUUID();
    }

    public Optional<IFortRole> getFortRoleForPlayer(UUID playerUUID) {
        Optional<FortPlayer> optional = getFortPlayersList().getPlayer(playerUUID);
        if (optional.isEmpty()) {
            return Optional.empty();
        }

        FortPlayer fortPlayer = optional.get();
        return getFortRolesSet().getRole(fortPlayer.getPlayerRoleIndex());
    }
}
