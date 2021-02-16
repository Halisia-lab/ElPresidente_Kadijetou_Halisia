package islandconfiguration;

import factions.*;

import java.util.ArrayList;
import java.util.Random;

public class Island {

    public static final int FOOD_UNIT_PER_PARTISAN = 4;
    private ArrayList<Faction> factions;
    private Agriculture agriculture;

    public Island() {
        this.factions = new ArrayList<Faction>();
    }

    public ArrayList<Faction> getFactions() {
        return this.factions;
    }

    public void setAgriculture(Agriculture agriculture) {
        this.agriculture = agriculture;
    }

    public void addFactions() {
        Capitalist capitalists = new Capitalist();
        Communist communists = new Communist();
        Ecologist ecologists = new Ecologist();
        Liberal liberals = new Liberal();
        Loyalist loyalists = new Loyalist();
        Militarist militarists = new Militarist();
        Nationalist nationalists = new Nationalist();
        Religious religious = new Religious();

        factions.add(capitalists);
        factions.add(communists);
        factions.add(ecologists);
        factions.add(liberals);
        factions.add(loyalists);
        factions.add(militarists);
        factions.add(nationalists);
        factions.add(religious);
    }

    public int getNumberOfPartisans() {
        int totalNumber = 0;
        for(Faction faction: this.factions) {
            totalNumber += faction.getNumberOfPartisans();
        }
        return totalNumber;
    }

    public Boolean isFoodUnitsEnough() {
        return (agriculture.calculateResourcesPerYear() / getNumberOfPartisans() >= FOOD_UNIT_PER_PARTISAN);
    }

    public Faction getRandomFaction(ArrayList<Faction> array) {
        int index = new Random().nextInt(array.size());
        return array.get(index);
    }

    public void decreaseAllSatisfactions(int percentageDecreased) {
        for(Faction faction: this.factions) {
            faction.decreaseSatisfaction(percentageDecreased);
        }
    }

    public String  randomPartisansElimination() {
        int foodUnits = agriculture.calculateResourcesPerYear();
        int extraPartisans = getNumberOfPartisans() - (foodUnits / FOOD_UNIT_PER_PARTISAN);
        int numberOfEliminations = extraPartisans;
        while (extraPartisans > 0) {
            Faction randomFaction = getRandomFaction(this.factions);

            if(randomFaction.getNumberOfPartisans() > 0) {
                randomFaction.eliminatePartisan();
                extraPartisans--;
                this.decreaseAllSatisfactions(2);
            }
        }
        return numberOfEliminations + " partisans have been eliminated.";
    }
}
