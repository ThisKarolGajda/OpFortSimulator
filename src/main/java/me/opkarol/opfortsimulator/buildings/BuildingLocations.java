package me.opkarol.opfortsimulator.buildings;

import me.opkarol.opfortsimulator.SimpleWorldLocation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuildingLocations implements Serializable {
    private final List<SimpleWorldLocation> locationList = new ArrayList<>();

    public BuildingLocations addLocation(SimpleWorldLocation simpleLocation) {
        locationList.add(simpleLocation);
        return this;
    }

    public BuildingLocations removeLocation(SimpleWorldLocation simpleLocation) {
        locationList.remove(simpleLocation);
        return this;
    }

    public List<SimpleWorldLocation> getLocationList() {
        return locationList;
    }
}