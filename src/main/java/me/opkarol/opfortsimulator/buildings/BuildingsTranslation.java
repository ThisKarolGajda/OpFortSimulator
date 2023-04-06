package me.opkarol.opfortsimulator.buildings;

import me.opkarol.opc.api.map.OpMap;

public class BuildingsTranslation extends OpMap<BuildingType, String> {

    {
        set(BuildingType.TOWN_HALL, "Ratusz");
    }

}
