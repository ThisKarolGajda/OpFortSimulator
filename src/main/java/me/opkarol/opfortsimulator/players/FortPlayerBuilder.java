package me.opkarol.opfortsimulator.players;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class FortPlayerBuilder extends FortPlayer implements Serializable {
    private final int index;

    public FortPlayerBuilder(String playerName, UUID playerUUID, int roleIndex) {
        super(playerName, playerUUID);
        this.index = roleIndex;
    }

    public FortPlayerBuilder(@NotNull Player leader, int roleIndex) {
        this(leader.getName(), leader.getUniqueId(), roleIndex);
    }

    @Override
    public int getPlayerRoleIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "FortPlayerBuilder{" +
                "index=" + index +
                ",uuid=" + getPlayerUUID() +
                ",name=" + getPlayerName() +
                '}';
    }
}
