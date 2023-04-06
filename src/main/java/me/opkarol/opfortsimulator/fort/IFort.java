package me.opkarol.opfortsimulator.fort;

import me.opkarol.opfortsimulator.SimpleLocation;
import me.opkarol.opfortsimulator.buildings.BuildingsList;
import me.opkarol.opfortsimulator.fort.roles.FortRolesSet;
import me.opkarol.opfortsimulator.players.FortPlayersList;

import java.io.Serializable;
import java.util.UUID;

public interface IFort extends Serializable {
    String getFortName();

    UUID getFortUUID();

    SimpleLocation getSpawnLocation();

    BuildingsList getBuildingsList();

    FortPlayersList getFortPlayersList();

    FortRangeBorders getFortRangeBorder();

    FortRolesSet getFortRolesSet();
}