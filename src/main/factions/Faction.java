package factions;
import java.util.ArrayList;

public abstract class Faction {
    private String name;
    private int satisfaction;
    private int numberOfPartisans;

    public Faction () {

    }

    public String getName() {
        return this.name;
    }

    public int getSatisfaction() {
        return this.satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getNumberOfPartisans() {
        return this.numberOfPartisans;
    }

    public void setNumberOfPartisans(int number) {
        this.numberOfPartisans = number;
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
