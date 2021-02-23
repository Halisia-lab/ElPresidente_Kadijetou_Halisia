package islandcaracteristics;

public class Agriculture implements ActivityArea {
    public final static int RATE_TO_GET_FOODUNITS = 40;

    private int percentage;

    public Agriculture() {

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
        return this.percentage * RATE_TO_GET_FOODUNITS;
    }
}
