package me.opkarol.opfortsimulator.fort;

import java.io.Serializable;

public class FortRangeBorders implements Serializable {
    private int x = 1, z = 1;
    public static int SINGLE_UNIT_LENGTH = 15;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "FortRangeBorders{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}
