package year;

import java.util.Random;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


import factions.Faction;
import game.Choice;
import game.Event;
import game.GameConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.toIntExact;

public class Year {
    private SeasonEnum season;
    private GameConfiguration game;

    public Year(GameConfiguration game) {
     this.season = SeasonEnum.WINTER;
     this.game = game;
    }

    public SeasonEnum getSeason() {
        return this.season;
    }

    public void printSeasons() {
        for(SeasonEnum season : SeasonEnum.values()) {
            System.out.println("We are in "+ season);
            Event newEvent = printRandomEvent();
            int choiceNumber = chooseAChoice(newEvent);
            newEvent.getChoices().get(choiceNumber).setPositiveImpacts(game.getIsland());
            newEvent.getChoices().get(choiceNumber).setPositiveImpacts(game.getIsland().getAgriculture());
            newEvent.getChoices().get(choiceNumber).setPositiveImpacts(game.getIsland().getIndustry());
            newEvent.getChoices().get(choiceNumber).setPositiveImpacts(game.getIsland().getTreasury());
            newEvent.getChoices().get(choiceNumber).setNegativeImpacts(game.getIsland());
            newEvent.getChoices().get(choiceNumber).setNegativeImpacts(game.getIsland().getAgriculture());
            newEvent.getChoices().get(choiceNumber).setNegativeImpacts(game.getIsland().getIndustry());
            newEvent.getChoices().get(choiceNumber).setNegativeImpacts(game.getIsland().getTreasury());
            for(Faction faction: this.game.getIsland().getFactions()) {
                newEvent.getChoices().get(choiceNumber).setPositiveImpacts(faction);
                newEvent.getChoices().get(choiceNumber).setNegativeImpacts(faction);
            }
        }
    }

    public Event printRandomEvent() {
        int eventsLength = this.game.getEvents().size();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, eventsLength);
        Event event = this.game.createEvent(randomIndex);
        System.out.println(event.getTitle());
        for(Choice choice: event.getChoices()) {
            System.out.println(choice.getTitle());
        }
        return event;
    }

    public int chooseAChoice(Event event) {
        int choice = 0, choicesLength = event.getChoices().size() - 1;
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of your choice :");

        while(!correctAnswer) {
            choice = sc.nextInt();
            if(choice < 1 || choice > choicesLength) {
                System.out.println("Please choose between " + 1 + " and " + choicesLength);
            } else {
                correctAnswer = true;
            }
        }
        return choice;
    }





}
