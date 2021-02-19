package islandcaracteristics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.GameConfiguration;
import org.junit.Test;

public class AppTest {

    private Island kadIsland;
    private GameConfiguration parameters;

    @Test
    public void setUp()
    {
        kadIsland = new Island();
        //kadIsland.addFactions();
        parameters = new GameConfiguration(kadIsland);
        parameters.setBacASableMode();
    }

    @Test
    public void testCalculateNumberOfPartisans() {
        setUp();
        assertEquals(120, kadIsland.getNumberOfPartisans());
    }

    @Test
    public void testIsFoodEnough() {
        setUp();
        assertEquals(true, kadIsland.areFoodUnitsEnough());
    }

    @Test
    public void testRandomPartisansElimination() {
        setUp();
        assertEquals("0 partisans have been eliminated.", kadIsland.randomPartisansElimination());
    }

    @Test
    public void testCheckPercentages() {
        setUp();
        assertEquals(true, kadIsland.checkPercentages());
    }


}
