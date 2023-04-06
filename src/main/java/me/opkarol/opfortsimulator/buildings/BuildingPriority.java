package me.opkarol.opfortsimulator.buildings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum BuildingPriority {
    HIGHEST(5),
    HIGH(4),
    MID(3),
    LOW(2),
    LOWEST(1);

    private final int priority;

    BuildingPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Contract(pure = true)
    public boolean hasHigherPriorityThan(@NotNull BuildingPriority buildingPriority) {
        return this.priority > buildingPriority.priority;
    }
}
