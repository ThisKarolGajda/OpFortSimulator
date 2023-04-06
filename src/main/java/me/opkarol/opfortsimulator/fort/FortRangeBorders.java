package me.opkarol.opfortsimulator.fort;

import java.io.Serializable;

public class FortRangeBorders implements Serializable {
    private int minusX = 1, minusY = 1, plusX = 1, plusY = 1;
    public static int SINGLE_UNIT_LENGTH = 15;

    public int getMinusX() {
        return minusX;
    }

    public void setMinusX(int minusX) {
        this.minusX = minusX;
    }

    public int getMinusY() {
        return minusY;
    }

    public void setMinusY(int minusY) {
        this.minusY = minusY;
    }

    public int getPlusX() {
        return plusX;
    }

    public void setPlusX(int plusX) {
        this.plusX = plusX;
    }

    public int getPlusY() {
        return plusY;
    }

    public void setPlusY(int plusY) {
        this.plusY = plusY;
    }

    @Override
    public String toString() {
        return "FortRangeBorders{" +
                "minusX=" + minusX +
                ", minusY=" + minusY +
                ", plusX=" + plusX +
                ", plusY=" + plusY +
                '}';
    }
}
