package islandcaracteristics;

import static org.junit.Assert.assertEquals;

import factions.Faction;
import game.GameConfiguration;
import org.junit.Test;
import year.Year;

import java.util.Scanner;

public class AppTest {
    private Year year;
    private GameConfiguration parameters;

    @Test
    public void setUp()
    {
        parameters = new GameConfiguration();
        year = new Year(parameters);
        parameters.initialization();
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

    @Test
    public void testGetGlobalSatsfaction() {
        setUp();
        assertEquals(56.25, parameters.getIsland().getGlobalSatisfaction(), 0.0001);
    }

    @Test
    public void testIsGlobalSatisfaction() {
        setUp();
        assertEquals(true, parameters.isGlobalSatisfaction());
    }

    @Test
    public void testRandomBirthsRepartition() {
        setUp();
        assertEquals("0 births were counted.", parameters.getIsland().randomBirthsRepartition());
    }

    @Test
    public void testAskWichFaction() {
        setUp();
        Scanner sc = new Scanner(System.in);
        Faction faction = parameters.getIsland().getFactions().get(5);
        int choice = sc.nextInt();
        assertEquals(faction, year.askWhichFaction());


    }
}
