package java.org.example;

public class Agriculture implements ActivityArea {
    public final static int RATE = 40;
    private int percentage;

    @Override
    public int calculateResourcesPerYear() {
        return this.percentage * RATE;
    }
}
