package me.opkarol.opfortsimulator;

import com.sk89q.worldguard.WorldGuard;
import me.opkarol.opc.api.plugins.OpMessagesPlugin;
import me.opkarol.opfortsimulator.commands.FortCommand;
import me.opkarol.opfortsimulator.fort.FortDatabase;
import me.opkarol.opfortsimulator.worldguard.WorldGuardAPI;

public final class OpFortSimulator extends OpMessagesPlugin {
    private static OpFortSimulator INSTANCE;
    private FortDatabase fortDatabase;
    private WorldGuardAPI worldGuardAPI;

    {
        INSTANCE = this;
    }

    public static OpFortSimulator getInstance() {
        return INSTANCE;
    }

    @Override
    public void enable() {
        fortDatabase = new FortDatabase(this);
        worldGuardAPI = new WorldGuardAPI(WorldGuard.getInstance().getPlatform().getRegionContainer());
    }

    public FortDatabase getFortDatabase() {
        return fortDatabase;
    }

    @Override
    public Object[] registerCommands() {
        return new Object[]{new FortCommand(fortDatabase),};
    }

    public WorldGuardAPI getWorldGuardAPI() {
        return worldGuardAPI;
    }
}
