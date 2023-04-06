package me.opkarol.opfortsimulator.fort.roles;

import java.io.Serializable;
import java.util.List;

public record FortRole(int index, List<FortPermission> permissions, String name) implements IFortRole, Serializable {
}
