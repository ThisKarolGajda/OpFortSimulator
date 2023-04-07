package me.opkarol.opfortsimulator.fort;

import me.opkarol.opc.api.tools.runnable.OpRunnable;
import me.opkarol.opfortsimulator.OpFortSimulator;
import me.opkarol.opfortsimulator.players.FortPlayerBuilder;
import me.opkarol.opfortsimulator.worldguard.WorldGuardAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static me.opkarol.opfortsimulator.fort.FortRangeBorders.SINGLE_UNIT_LENGTH;

public class FortManager {
    private static final FortDatabase FORT_DATABASE = OpFortSimulator.getInstance().getFortDatabase();
    private static final WorldGuardAPI WORLD_GUARD_API = OpFortSimulator.getInstance().getWorldGuardAPI();

    @Deprecated
    public static FORT_CREATE_RESPONSE createAndRegisterFort(@NotNull IFort fort, Player leader) {
        if (FORT_DATABASE.hasFortWithTheSameName(fort.getFortName())) {
            return FORT_CREATE_RESPONSE.ALREADY_EXISTING_FORT;
        }

        // Check if player doesn't already have a fort / or is a member in one
        if (FORT_DATABASE.getFortFromPlayer(leader.getUniqueId()).isPresent()) {
            return FORT_CREATE_RESPONSE.ALREADY_HAS_FORT;
        }

        Location location = leader.getLocation();
        Location location1 = location.clone().add(-SINGLE_UNIT_LENGTH, 0, -SINGLE_UNIT_LENGTH);
        location1.setY(-64);
        Location location2 = location.clone().add(SINGLE_UNIT_LENGTH, 0, SINGLE_UNIT_LENGTH);
        location2.setY(320);

        // Check if region isn't occupied
        try {
            if (!WORLD_GUARD_API.isRegionNotOccupied(location1, location2).get()) {
                return FORT_CREATE_RESPONSE.REGION_OCCUPIED;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        FORT_DATABASE.insertFort(fort);
        // Add default roles - [LEADER, ADMIN, MOD, MEMBER]
        fort.getFortRolesSet().loadDefaultRoles();
        // Add leader to players list
        fort.getFortPlayersList().addPlayer(new FortPlayerBuilder(leader, 0));

        // Register region to WorldGuard
        WORLD_GUARD_API.addRegionWithOwner(leader, fort.toString(), location1, location2);
        return FORT_CREATE_RESPONSE.SUCCESS;
    }

    public static @NotNull CompletableFuture<FORT_CREATE_RESPONSE> createAndRegisterFortAsync(@NotNull IFort fort, Player leader) {
        CompletableFuture<FORT_CREATE_RESPONSE> future = new CompletableFuture<>();

        new OpRunnable(() -> {
            if (FORT_DATABASE.hasFortWithTheSameName(fort.getFortName())) {
                future.complete(FORT_CREATE_RESPONSE.ALREADY_EXISTING_FORT);
                return;
            }

            // Check if player doesn't already have a fort / or is a member in one
            if (FORT_DATABASE.getFortFromPlayer(leader.getUniqueId()).isPresent()) {
                future.complete(FORT_CREATE_RESPONSE.ALREADY_HAS_FORT);
                return;
            }

            Location location = leader.getLocation();
            Location location1 = location.clone().add(-SINGLE_UNIT_LENGTH, 0, -SINGLE_UNIT_LENGTH);
            location1.setY(-64);
            Location location2 = location.clone().add(SINGLE_UNIT_LENGTH, 0, SINGLE_UNIT_LENGTH);
            location2.setY(320);

            // Check if region isn't occupied
            try {
                if (!WORLD_GUARD_API.isRegionNotOccupied(location1, location2).get()) {
                    future.complete(FORT_CREATE_RESPONSE.REGION_OCCUPIED);
                    return;
                }
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
                return;
            }

            FORT_DATABASE.insertFort(fort);
            // Add default roles - [LEADER, ADMIN, MOD, MEMBER]
            fort.getFortRolesSet().loadDefaultRoles();
            // Add leader to players list
            fort.getFortPlayersList().addPlayer(new FortPlayerBuilder(leader, 0));

            // Register region to WorldGuard
            WORLD_GUARD_API.addRegionWithOwner(leader, fort.toString(), location1, location2);

            future.complete(FORT_CREATE_RESPONSE.SUCCESS);
        }).runTaskAsynchronously();

        return future;
    }

    public enum FORT_CREATE_RESPONSE {
        UNKNOWN,
        SUCCESS,
        ALREADY_EXISTING_FORT,
        REGION_OCCUPIED,
        ALREADY_HAS_FORT,

    }
}
