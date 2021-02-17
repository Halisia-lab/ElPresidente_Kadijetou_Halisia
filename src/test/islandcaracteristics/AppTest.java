package islandcaracteristics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

    private Island kadIsland;
    private  Agriculture agriculture;
    private Industry industry;

    @Test
    public void setUp()
    {
        kadIsland = new Island();
        agriculture = new Agriculture();
        industry = new Industry();
        kadIsland.addFactions();
        kadIsland.setAgriculture(agriculture);
        kadIsland.setIndustry(industry);
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

    @Test
    public void testCheckPercentages() {
        setUp();
        assertEquals(true, kadIsland.checkPercentages());
    }



}
