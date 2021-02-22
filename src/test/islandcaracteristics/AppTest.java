package islandcaracteristics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.GameConfiguration;
import org.junit.Test;

public class AppTest {

    private GameConfiguration parameters;

    @Test
    public void setUp()
    {
        //kadIsland.addFactions();
        parameters = new GameConfiguration();
        parameters.setBacASableMode();
    }

    @Test
    public void testCalculateNumberOfPartisans() {
        setUp();
        assertEquals(120, parameters.getIsland().getNumberOfPartisans());
    }

    @Test
    public void testIsFoodEnough() {
        setUp();
        assertEquals(true, parameters.getIsland().areFoodUnitsEnough());
    }

    @Test
    public void testRandomPartisansElimination() {
        setUp();
        assertEquals("0 partisans have been eliminated.", parameters.getIsland().randomPartisansElimination());
    }

    @Test
    public void testCheckPercentages() {
        setUp();
        assertEquals(true, parameters.getIsland().checkPercentages());
    }


}
