package me.opkarol.opfortsimulator.commands;

import me.opkarol.opfortsimulator.OpFortSimulator;
import me.opkarol.opfortsimulator.SimpleLocation;
import me.opkarol.opfortsimulator.fort.*;
import me.opkarol.opfortsimulator.fort.roles.IFortRole;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.DefaultFor;
import revxrsal.commands.annotation.Subcommand;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static me.opkarol.opc.api.utils.VariableUtil.stringValueOfMap;

@Command("fort")
@SuppressWarnings("unused")
public class FortCommand {
    private final FortDatabase fortDatabase;

    public FortCommand(FortDatabase fortDatabase) {
        this.fortDatabase = fortDatabase;
    }

    @DefaultFor("fort")
    public void main(Player sender) {
        sender.sendMessage(stringValueOfMap(OpFortSimulator.getInstance().getFortDatabase().getMap()));
    }

    @Subcommand("create")
    public void createFort(@NotNull Player player, String fortName) {
        IFort fort = new FortBuilder(fortName, SimpleLocation.of(player));
        player.sendMessage(FortManager.createAndRegisterFort(fort, player).name());
    }

    @Subcommand("info")
    public void getInfo(Player player) {
        Optional<IFort> optional = OpFortSimulator.getInstance().getFortDatabase().getFortFromPlayer(player.getUniqueId());
        if (optional.isEmpty()) {
            player.sendMessage("You don't have fort");
            return;
        }

        IFort fort = optional.get();
        //TODO remove this debug
        // replace with gui
        player.sendMessage("FORT SPAWN LOCATION: " + fort.getSpawnLocation().toString());
        player.sendMessage("FORT ROLES: " + Arrays.toString(fort.getFortRolesSet().getRolesSet().toArray()));
        player.sendMessage("FORT NAME: " + fort.getFortName());
        player.sendMessage("FORT UUID: " + fort.getFortUUID());
        player.sendMessage("FORT PLAYERS: " + Arrays.toString(fort.getFortPlayersList().getFortPlayers().toArray()));
        player.sendMessage("FORT RANGE BORDERS: " + fort.getFortRangeBorder().toString());
        player.sendMessage("BUILDINGS LIST: " + Arrays.toString(fort.getBuildingsList().getBuildingList().toArray()));
    }

    @Subcommand("leave")
    public void leaveFort(Player player) {
        UUID uuid = player.getUniqueId();
        Optional<IFort> fortOptional = fortDatabase.getFortFromPlayer(uuid);
        if (fortOptional.isEmpty()) {
            player.sendMessage("Don't have fort!");
            return;
        }
        // todo ask twice for confirmation - or like /... leave confirm
        Fort fort = (Fort) fortOptional.get();
        Optional<IFortRole> fortRoleOptional = fort.getFortRoleForPlayer(uuid);

        if (fortRoleOptional.isPresent()) {
            IFortRole fortRole = fortRoleOptional.get();
            if (fortRole.isLeaderRole()) {
                fortDatabase.removeFort(fort);
                OpFortSimulator.getInstance().getWorldGuardAPI().removeRegion(player.getWorld(), fort.toString());
                player.sendMessage("Removed fort!");
                return;
            }
        }
        fort.getFortPlayersList().deletePlayer(uuid);
    }

}
