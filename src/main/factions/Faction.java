package factions;
import java.util.ArrayList;

public abstract class Faction {
    private int satisfaction;
    private int numberOfPartisans;

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

    public void eliminatePartisan() {
        this.numberOfPartisans --;
    }

    public boolean isSatisfactionEqualsZero () {
        return this.satisfaction == 0;
    }

    public void increaseSatisfaction (int percentageAdded) {
        if(!isSatisfactionEqualsZero()) {
            this.satisfaction += percentageAdded;
        }
        if(this.satisfaction > 100) {
            this.satisfaction = 100;
        }
    }

    public void decreaseSatisfaction (int percentageDecreased) {
        if(this.satisfaction - percentageDecreased >= 0) {
            this.satisfaction -= percentageDecreased;
        } else {
            this.satisfaction = 0;
        }
    }


}
