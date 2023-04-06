package me.opkarol.opfortsimulator.buildings.types;

import me.opkarol.opfortsimulator.buildings.Building;
import me.opkarol.opfortsimulator.buildings.BuildingType;
import org.bukkit.World;

public class TownHallBuilding extends Building {

    public TownHallBuilding(World world) {
        super(world);
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.TOWN_HALL;
    }

}
