package java.org.example;

import java.util.ArrayList;

public abstract class Faction {
    private int satisfaction;
    protected int numberOfPartisans;

    public Faction () {
        this.satisfaction = 15;
        this.numberOfPartisans = 15;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getNumberOfPartisans() {
        return numberOfPartisans;
    }

    public int eliminatePartisan() {
        this.numberOfPartisans --;
        return 1;
    }

    public boolean isSatisfactionEqualsZero () {
        return this.satisfaction == 0;
    }

    public void increaseSatisfaction (int percentageAdded) {
        if (!isSatisfactionEqualsZero()) {
            this.satisfaction += percentageAdded;
        }
    }
}
