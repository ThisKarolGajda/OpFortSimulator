package me.opkarol.opfortsimulator.buildings;

import me.opkarol.opfortsimulator.SimpleLocation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuildingLocations implements Serializable {
    private final List<SimpleLocation> locationList = new ArrayList<>();

    public BuildingLocations addLocation(SimpleLocation simpleLocation) {
        locationList.add(simpleLocation);
        return this;
    }

    public BuildingLocations removeLocation(SimpleLocation simpleLocation) {
        locationList.remove(simpleLocation);
        return this;
    }

    public List<SimpleLocation> getLocationList() {
        return locationList;
    }
}