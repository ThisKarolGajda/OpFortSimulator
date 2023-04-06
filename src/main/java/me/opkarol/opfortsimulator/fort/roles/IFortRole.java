package me.opkarol.opfortsimulator.fort.roles;

import java.io.Serializable;
import java.util.List;

public interface IFortRole extends Serializable {
    int index();

    List<FortPermission> permissions();

    String name();

    default boolean isLeaderRole() {
        return index() == 0;
    }
}
