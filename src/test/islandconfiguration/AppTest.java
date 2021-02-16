package islandconfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

    private Island kadIsland;
    private  Agriculture agriculture;

    @Test
    public void setUp()
    {
        kadIsland = new Island();
        agriculture = new Agriculture();
        kadIsland.addFactions();
        kadIsland.setAgriculture(agriculture);
    }

    @Test
    public void testCalculateNumberOfPartisans() {
        setUp();
        assertEquals(120, kadIsland.getNumberOfPartisans());
    }

    @Test
    public void testIsFoodEnough() {
        setUp();
        assertEquals(false, kadIsland.isFoodUnitsEnough());
    }

    @Test
    public void testRandomPartisansElimination() {
        setUp();
        assertEquals("20 partisans have been eliminated.", kadIsland.randomPartisansElimination());
    }
    


}
