package org.example;

public class Agriculture implements ActivityArea {
    public final static int RATE = 40;
    private int percentage;

    public Agriculture() {
        this.percentage = 3;
    }

    @Override
    public int calculateResourcesPerYear() {
        return this.percentage * RATE;
    }
}
