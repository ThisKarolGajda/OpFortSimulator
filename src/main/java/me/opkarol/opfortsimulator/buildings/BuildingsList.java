package me.opkarol.opfortsimulator.buildings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuildingsList implements Serializable {
    private final List<Building> buildingList = new ArrayList<>();

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public BuildingsList addBuilding(Building building) {
        buildingList.add(building);
        return this;
    }

    public void removeBuilding(Building building) {
        buildingList.remove(building);
    }

    public Building getBuilding(int index) {
        return buildingList.get(index);
    }
}
