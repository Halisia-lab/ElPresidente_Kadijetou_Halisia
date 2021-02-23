package islandcaracteristics;

import factions.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Island {

    public static final int FOOD_UNIT_PER_PARTISAN = 4;
    public static final int FOOD_UNIT_PRICE = 8;
    public static final int BRIBE_PRICE_PER_PARTISAN = 15;
    public static final int INCREASED_SATISFACTION_AFTER_BRIBE = 10;

    private ArrayList<Faction> factions;
    private Agriculture agriculture;
    private Industry industry;
    private Treasury treasury;
    private int purchasedFoodUnits;
    private Communist communists = new Communist();
    private Capitalist capitalists = new Capitalist();
    private Ecologist ecologists = new Ecologist();
    private Liberal liberals = new Liberal();
    private Loyalist loyalists = new Loyalist();
    private Militarist militarists = new Militarist();
    private Nationalist nationalists = new Nationalist();
    private Religious religious = new Religious();

    public Island() {
        this.factions = new ArrayList<Faction>();
        this.agriculture = new Agriculture();
        this.industry = new Industry();
        this.treasury = new Treasury();
        this.purchasedFoodUnits = 0;
        factions.add(communists);
        factions.add(capitalists);
        factions.add(ecologists);
        factions.add(liberals);
        factions.add(loyalists);
        factions.add(militarists);
        factions.add(nationalists);
        factions.add(religious);
    }

    public ArrayList<Faction> getFactions() {
        return this.factions;
    }

    public Agriculture getAgriculture() {
        return agriculture;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Treasury getTreasury() {
        return treasury;
    }

    public int getPurchasedFoodUnits() {
        return purchasedFoodUnits;
    }

    public Communist getCommunists() {
        return communists;
    }

    public Capitalist getCapitalists() {
        return capitalists;
    }

    public Ecologist getEcologists() {
        return ecologists;
    }

    public Liberal getLiberals() {
        return liberals;
    }

    public Loyalist getLoyalists() {
        return loyalists;
    }

    public Militarist getMilitarists() {
        return militarists;
    }

    public Nationalist getNationalists() {
        return nationalists;
    }

    public Religious getReligious() {
        return religious;
    }

    public int calculateFoodUnits() {
        return this.agriculture.calculateResourcesPerYear() + this.purchasedFoodUnits;
    }

    public Boolean checkPercentages() {
        return this.industry.getPercentage() + this.agriculture.getPercentage() <= 100;
    }

    /*public void addFactions() {
        factions.add(communists);
        factions.add(capitalists);
        factions.add(ecologists);
        factions.add(liberals);
        factions.add(loyalists);
        factions.add(militarists);
        factions.add(nationalists);
        factions.add(religious);
    }*/


    public int getNumberOfPartisans() {
        int totalNumber = 0;
        for(Faction faction: this.factions) {
            totalNumber += faction.getNumberOfPartisans();
        }
        return totalNumber;
    }

    public double getGlobalSatisfaction() {
        double globalSatisfaction = 0;
        for(Faction faction: this.factions) {
            globalSatisfaction += faction.getSatisfaction() * faction.getNumberOfPartisans();
        }
        return globalSatisfaction / this.getNumberOfPartisans();
    }

    public Boolean areFoodUnitsEnough() {
        return (this.calculateFoodUnits() / getNumberOfPartisans() >= FOOD_UNIT_PER_PARTISAN);
    }

    public Boolean isAgricultureEnough() {
        return this.agriculture.calculateResourcesPerYear()/ getNumberOfPartisans() >= FOOD_UNIT_PER_PARTISAN;
    }

    public Faction getRandomFaction(ArrayList<Faction> array) {
        int index = new Random().nextInt(array.size());
        return array.get(index);
    }

    public void decreaseAllSatisfactions(int percentageDecreased) {
        for(Faction faction: factions) {
            faction.decreaseSatisfaction(percentageDecreased);
        }
    }

    public String  randomPartisansElimination() {
        System.out.println("Food units are missing.. some partisans will be eliminated now.");
        int extraPartisans = getNumberOfPartisans() - (this.calculateFoodUnits() / FOOD_UNIT_PER_PARTISAN);
        if(extraPartisans < 0) {
            extraPartisans = 0;
        }
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

    public String randomBirthsRepartition() {
        System.out.println("There is a lot of agriculture ! New births are coming...");
        double randomPercentage = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        double extraPartisans = (randomPercentage / 100) * this.getNumberOfPartisans();
        int numberOfBirths = (int)extraPartisans;
        while (extraPartisans >= 1) {
            Faction randomFaction = getRandomFaction(this.factions);
            randomFaction.setNumberOfPartisans(randomFaction.getNumberOfPartisans() + 1);
            extraPartisans--;
        }
        return (int) numberOfBirths + " births were counted.";
    }

    public void randomBirthsRepartition(int number) {
        double extraPartisans = number;
        int numberOfBirths = (int)extraPartisans;
        while (extraPartisans >= 1) {
            Faction randomFaction = getRandomFaction(this.factions);
            randomFaction.setNumberOfPartisans(randomFaction.getNumberOfPartisans() + 1);
            extraPartisans--;
        }
    }

    public void purchaseFoodUnits() {
        Scanner sc = new Scanner(System.in);
        Boolean enoughMoney = false;
        int quantity = 0, totalAmount = 0;
        System.out.println("How many food units do you want to buy ? You have " + this.treasury.getMoneyAvailable());
        do {
            quantity = sc.nextInt();
            totalAmount = quantity * FOOD_UNIT_PRICE;
            if(totalAmount > this.treasury.getMoneyAvailable()) {
                System.out.println("You don't have enough money, you can't buy more than " + (int) this.treasury.getMoneyAvailable() / FOOD_UNIT_PRICE + " units");
            } else {
                enoughMoney = true;
                //totalAmount = quantity * FOOD_UNIT_PRICE;
            }
        } while (!enoughMoney);
        this.treasury.setMoneyAvailable(this.treasury.getMoneyAvailable() - totalAmount);
        this.purchasedFoodUnits += quantity;

        System.out.println("You bougth " + quantity + " food units, " + this.treasury.getMoneyAvailable() + "$ are left.");
        sc.close();
    }

    public void organizeABribe(Faction faction) {
        int price = BRIBE_PRICE_PER_PARTISAN * faction.getNumberOfPartisans();
        if(price > this.treasury.getMoneyAvailable()) {
            System.out.println("You don't have enough money...");
        } else {
            int loyalistsSatisfactionDecreased = price / 10;
            //15$ per partisans
            System.out.println("price: "+ price);
            this.treasury.setMoneyAvailable(this.treasury.getMoneyAvailable() - price);

            //+10% satisfaction
            faction.increaseSatisfaction(INCREASED_SATISFACTION_AFTER_BRIBE);

            // new loyalists satisfaction
            this.loyalists.decreaseSatisfaction(loyalistsSatisfactionDecreased);

            System.out.println(faction.getName() + " has +" + INCREASED_SATISFACTION_AFTER_BRIBE +"% satisfaction after the bribe ! " );
            System.out.println(this.getLoyalists().getName() + " has -" + loyalistsSatisfactionDecreased +"% satisfaction... " );
            System.out.println(this.treasury.getMoneyAvailable() + "$ are left.");
            for(Faction oneFaction:this.getFactions()){
                System.out.println(oneFaction.getName() +": "+oneFaction.getSatisfaction());
            }
        }

    }
}
