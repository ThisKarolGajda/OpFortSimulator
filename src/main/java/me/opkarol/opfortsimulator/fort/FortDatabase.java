package me.opkarol.opfortsimulator.fort;

import me.opkarol.opc.api.database.flat.FlatDatabase;
import me.opkarol.opc.api.map.OpMap;
import me.opkarol.opc.api.plugins.OpPlugin;
import me.opkarol.opc.api.tools.autostart.IDisable;
import me.opkarol.opc.api.tools.autostart.OpAutoDisable;

import java.util.Optional;
import java.util.UUID;

public class FortDatabase extends FlatDatabase<OpMap<UUID, IFort>> implements IDisable {
    private final OpMap<UUID, IFort> map;
    private static FortDatabase INSTANCE;

    {
        INSTANCE = this;
    }

    public FortDatabase(OpPlugin plugin) {
        super(plugin, "fort-database.yml");
        OpMap<UUID, IFort> map = loadObject();
        if (map == null) {
            map = new OpMap<>();
        }
        this.map = map;
        OpAutoDisable.add(this);
    }

    public static FortDatabase getInstance() {
        return INSTANCE;
    }

    @Override
    public void onDisable() {
        saveObject(this.map);
    }

    public OpMap<UUID, IFort> getMap() {
        return this.map;
    }

    public Optional<IFort> getFort(UUID fortUUID) {
        return map.getByKey(fortUUID);
    }

    public boolean hasFort(UUID fortUUID) {
        return getFort(fortUUID).isPresent();
    }

    public void insertFort(IFort fort) {
        map.set(fort.getFortUUID(), fort);
    }

    public void removeFort(UUID uuid) {
        map.remove(uuid);
    }

    public void removeFort(IFort fort) {
        map.remove(fort.getFortUUID(), fort);
    }

    public UUID getUnusedFortUUID() {
        UUID uuid = UUID.randomUUID();
        while (map.containsKey(uuid)) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

    public boolean hasFortWithTheSameName(String name) {
        return map.getValuesStream()
                .anyMatch(fort -> fort.getFortName().equals(name));
    }

    public Optional<IFort> getFortFromPlayer(UUID playerUUID) {
        return map.getValuesStream()
                .filter(fort -> fort.getFortPlayersList().containsPlayer(playerUUID))
                .findFirst();
    }
}
