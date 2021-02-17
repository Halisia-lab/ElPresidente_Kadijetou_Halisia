package islandcaracteristics;

public class Industry implements ActivityArea {
    public static final int RATE_TO_GET_DOLLARS = 10;

    int percentage;

    public Industry() {
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
        return this.percentage * RATE_TO_GET_DOLLARS;
    }
}
