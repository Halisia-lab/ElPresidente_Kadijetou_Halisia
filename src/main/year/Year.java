package year;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import factions.Faction;
import game.Choice;
import game.Event;
import game.GameConfiguration;


public class Year {
    private SeasonEnum season;
    private GameConfiguration game;
    private int currentEvent;

    public Year(GameConfiguration game) {
        this.season = SeasonEnum.WINTER;
        this.game = game;
        this.currentEvent = 0;
    }
    public GameConfiguration getGame() {
        return game;
    }

    public void playSeasons() {
        for (SeasonEnum season : SeasonEnum.values()) {
            System.out.println("--------------------------------------------\nWE ARE IN " + season + "\n");
            this.game.getIsland().printFactions();
            Event newEvent = new Event();
            if(this.game.getMode() == 1) { //if it's the sandbox mode
                newEvent = printRandomEvent();
            } else { //if it's the scenario mode
                newEvent = printNextEvent();
            }
            int choiceNumber = chooseAChoice(newEvent) - 1;

            //applying all consequences
            newEvent.getChoices().get(choiceNumber).applyImpacts(game.getIsland());
            newEvent.getChoices().get(choiceNumber).applyImpacts(game.getIsland().getAgriculture());
            newEvent.getChoices().get(choiceNumber).applyImpacts(game.getIsland().getIndustry());
            newEvent.getChoices().get(choiceNumber).applyImpacts(game.getIsland().getTreasury());
            for (Faction faction : this.game.getIsland().getFactions()) {
                newEvent.getChoices().get(choiceNumber).applyImpacts(faction);
            }
        }
        endOfYearAction();
        System.out.println("");
        endOfYearChecking();
        System.out.println("\n\nResults of this year :");
        this.game.getIsland().printFactions();
    }

    public Event printRandomEvent() {
        int eventsLength = this.game.getEvents().size();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, eventsLength);
        Event event = this.game.createEvent(randomIndex);
        System.out.println(event.getTitle());
        for (Choice choice : event.getChoices()) {
            System.out.println(choice.getTitle());
        }
        System.out.println("");
        return event;
    }

    public Event printNextEvent() {
        int eventsLength = this.game.getEvents().size();
        Event event = this.game.createEvent(this.currentEvent);
        System.out.println(event.getTitle());
        for (Choice choice : event.getChoices()) {
            System.out.println(choice.getTitle());
        }
        System.out.println("");
        this.currentEvent++;
        if(this.currentEvent == eventsLength - 1) {
            this.currentEvent = 0;
        }
        return event;
    }



    public int chooseAChoice(Event event) {
        int choice = 0, choicesLength = event.getChoices().size();
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of your choice :");
        while (!correctAnswer && sc.hasNextInt()) {
            choice = sc.nextInt();
            if (choice < 1 || choice > choicesLength) {
                System.out.println("Please choose between " + 1 + " and " + choicesLength);
            } else {
                correctAnswer = true;
            }
        }
        return choice;
    }

    public Faction askWhichFaction() { //used when the user decide to organize a bribe
        Boolean correctAnswer = false;
        int choice = 0, factionsLength = this.game.getIsland().getFactions().size();
        Scanner sc = new Scanner(System.in);
        System.out.println("For which faction ? (Enter the correspondant number)");
        for(int i = 1; i <= factionsLength; i++) {
            System.out.println(i + ". " + this.game.getIsland().getFactions().get(i-1).getName());
        }
        while (!correctAnswer && sc.hasNextInt()) {
            choice = sc.nextInt();
            if (choice < 1 || choice > factionsLength) {
                System.out.println("Please choose between " + 1 + " and " + factionsLength);
            } else {
                correctAnswer = true;
            }
        }
        return this.game.getIsland().getFactions().get(choice -1);
    }


    public void endOfYearAction() {
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println("\n This is the end of the year, do you want to do ?");
        System.out.println("1: Organize a bribe\n2: Buy some food units\n3: Nothing");

        while (!correctAnswer) {
            choice = sc.nextInt();

            if (choice < 1 || choice > 3) {
                System.out.println("Choose between 1 and 3...");
            } else {
                correctAnswer = true;
            }
        }
        switch (choice) {
            case 1:
                Faction faction = askWhichFaction();
                this.game.getIsland().organizeABribe(faction);
                break;
            case 2:
                this.game.getIsland().purchaseFoodUnits();
                break;
            case 3:
                System.out.println("OK.");
                break;
        }
    }

    public void endOfYearChecking() {
        if(!this.game.getIsland().areFoodUnitsEnough()) {
            System.out.println(this.game.getIsland().randomPartisansElimination());
        } else if (this.game.getIsland().isAgricultureEnough()) {
            System.out.println(this.game.getIsland().randomBirthsRepartition());
        }
    }
}
