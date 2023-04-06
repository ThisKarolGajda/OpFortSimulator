package me.opkarol.opfortsimulator.fort.roles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FortRolesSet implements Serializable {
    private final HashSet<IFortRole> rolesSet = new HashSet<>();

    public HashSet<IFortRole> getRolesSet() {
        return rolesSet;
    }

    public void addRole(IFortRole fortRole) {
        rolesSet.add(fortRole);
    }

    public void removeRole(IFortRole fortRole) {
        rolesSet.remove(fortRole);
    }

    public Optional<IFortRole> getRole(int index) {
        return rolesSet.stream()
                .filter(fortRole -> fortRole.index() == index)
                .findFirst();
    }

    public List<IFortRole> getRolesFromPermissions(FortPermission fortPermission) {
        return rolesSet.stream()
                .filter(fortRole -> fortRole.permissions().contains(fortPermission))
                .collect(Collectors.toList());
    }

    public Optional<IFortRole> getRole(String name) {
        return rolesSet.stream()
                .filter(fortRole -> fortRole.name().equals(name))
                .findFirst();
    }

    public void loadDefaultRoles() {
        getRolesSet().clear();
        addRole(new FortRole(0, List.of(), "LEADER"));
        addRole(new FortRole(1, List.of(), "ADMIN"));
        addRole(new FortRole(2, List.of(), "MOD"));
        addRole(new FortRole(3, List.of(), "MEMBER"));
    }
}
