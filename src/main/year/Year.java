package year;

import java.util.Random;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


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
            System.out.println(season);
        }
    }

    public void printEventTitle() {
        int eventsLength = this.game.getEvents().size();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, eventsLength);
        System.out.println(this.game.createEvent(randomIndex).getTitle());
    }





}
