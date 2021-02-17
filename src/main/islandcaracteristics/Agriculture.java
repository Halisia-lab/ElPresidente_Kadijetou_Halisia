package islandcaracteristics;

public class Agriculture implements ActivityArea {
    public final static int RATE_TO_GET_FOODUNITS = 40;

    private int percentage;

    public Agriculture() {
        this.percentage = 10;
    }

    @Override
    public int getPercentage() {
        return this.percentage;
    }

    @Override
    public void setPercentage(int percentage) {
        if(percentage <= 100 && percentage >= 0) {
            this.percentage = percentage;
        }
    }

    @Override
    public int calculateResourcesPerYear() {
        return this.percentage * RATE_TO_GET_FOODUNITS;
    }
}
