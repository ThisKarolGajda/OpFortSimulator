package me.opkarol.opfortsimulator.fort;

import me.opkarol.opfortsimulator.OpFortSimulator;
import me.opkarol.opfortsimulator.SimpleLocation;

import java.util.UUID;

public class FortBuilder extends Fort {
    private final String fortName;
    private final UUID fortUUID;

    public FortBuilder(String fortName, SimpleLocation spawnLocation) {
        FortDatabase fortDatabase = OpFortSimulator.getInstance().getFortDatabase();
        this.fortName = fortName;
        this.fortUUID = fortDatabase.getUnusedFortUUID();
        this.setSpawnLocation(spawnLocation);
    }

    @Override
    public String getFortName() {
        return fortName;
    }

    @Override
    public UUID getFortUUID() {
        return fortUUID;
    }
}
