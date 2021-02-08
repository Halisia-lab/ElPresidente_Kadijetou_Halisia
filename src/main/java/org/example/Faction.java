package java.org.example;

public abstract class Faction {
    private int satisfaction;


    public Faction (int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getSatisfaction() {
        return satisfaction;
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
