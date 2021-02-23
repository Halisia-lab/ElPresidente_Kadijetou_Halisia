package islandcaracteristics;

public class Industry implements ActivityArea {
    public static final int RATE_TO_GET_DOLLARS = 10;

    int percentage;

    public Industry() {

    }

    @Override
    public int getPercentage() {
        return this.percentage;
    }

    @Override
    public void setPercentage(int percentage) {
        this.percentage = percentage;
        if(percentage < 0) {
            percentage = 0;
        }
    }

    @Override
    public int calculateResourcesPerYear() {
        return this.percentage * RATE_TO_GET_DOLLARS;
    }
}
