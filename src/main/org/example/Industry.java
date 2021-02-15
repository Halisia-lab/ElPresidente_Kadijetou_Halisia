package org.example;

public class Industry implements ActivityArea {
    public static final int RATE = 10;
    int percentage;

    @Override
    public int calculateResourcesPerYear() {
        return this.percentage * RATE;
    }
}
