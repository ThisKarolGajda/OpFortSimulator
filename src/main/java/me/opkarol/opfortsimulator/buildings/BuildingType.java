package me.opkarol.opfortsimulator.buildings;

public enum BuildingType {
    TOWN_HALL(BuildingPriority.HIGHEST),
    ;

    private final BuildingPriority priority;

    BuildingType(BuildingPriority priority) {
        this.priority = priority;
    }

    public BuildingPriority getPriority() {
        return priority;
    }
}
