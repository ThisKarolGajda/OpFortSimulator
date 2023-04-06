package me.opkarol.opfortsimulator.players;

import java.io.Serializable;
import java.util.UUID;

public abstract class FortPlayer implements IFortPlayer, Serializable {
    private final String playerName;
    private final UUID playerUUID;

    public FortPlayer(String playerName, UUID playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
