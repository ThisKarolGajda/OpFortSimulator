package me.opkarol.opfortsimulator.buildings;

import org.bukkit.World;

import java.io.Serializable;

public abstract class Building implements IBuilding, Serializable {
    private final BuildingLocations buildingLocations;
    private final World world;

    public Building(World world) {
        this.world = world;
        this.buildingLocations = new BuildingLocations();
    }

    @Override
    public BuildingLocations getBuildingLocations() {
        return buildingLocations;
    }

    @Override
    public WorldBorderRegionType getWorldBorderRegionType() {
        return WorldBorderRegionType.BUILDING_EGG;
    }

    public World getWorld() {
        return world;
    }
}
