package me.opkarol.opfortsimulator.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FortPlayersList implements Serializable {
    private final List<FortPlayer> fortPlayers = new ArrayList<>();

    public List<FortPlayer> getFortPlayers() {
        return fortPlayers;
    }

    public FortPlayersList addPlayer(FortPlayer building) {
        fortPlayers.add(building);
        return this;
    }

    public FortPlayer getPlayer(int index) {
        return fortPlayers.get(index);
    }

    public boolean containsPlayer(UUID playerUUID) {
        return getPlayer(playerUUID).isPresent();
    }

    public Optional<FortPlayer> getPlayer(UUID playerUUID) {
        return fortPlayers.stream()
                .filter(fortPlayer -> fortPlayer.getPlayerUUID().equals(playerUUID))
                .findFirst();
    }

    public void deletePlayer(UUID playerUUID) {
        fortPlayers.removeIf(fortPlayer -> fortPlayer.getPlayerUUID().equals(playerUUID));
    }
}
