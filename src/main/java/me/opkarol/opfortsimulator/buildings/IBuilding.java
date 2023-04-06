package me.opkarol.opfortsimulator.buildings;

public interface IBuilding {

    WorldBorderRegionType getWorldBorderRegionType();

    BuildingType getBuildingType();

    BuildingLocations getBuildingLocations();
}
