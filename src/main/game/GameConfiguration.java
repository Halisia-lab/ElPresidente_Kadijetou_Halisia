package game;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

import factions.Faction;
import islandcaracteristics.Island;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import year.Year;

import static java.lang.Math.toIntExact;


public class GameConfiguration {

    private int difficulty;
    private Island island;
    //private Year year;
    private JSONObject initializationFile;
    private JSONObject configurationFile;
    private String scenario;
    private JSONArray events;
    private int globalSatisfaction;
    private JSONArray endOfYear;

    public GameConfiguration() {
        this.island = new Island();
        //this.year = new Year();
    }

    public JSONObject getInitializationFile() {
        return initializationFile;
    }

    public JSONObject getConfigurationFile() {
        return configurationFile;
    }

    public Island getIsland() {
        return island;
    }

    public String getScenario() {
        return scenario;
    }

    public JSONArray getEvents() {
        return events;
    }

    public int getGlobalSatisfaction() {
        return globalSatisfaction;
    }

    public JSONArray getEndOfYear() {
        return endOfYear;
    }

    /*public Year getYear() {
        return year;
    }*/

    public void setBacASableMode() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("files/bacasable.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray bacASableArray = (JSONArray) obj;

            //Iterate over employee array
            bacASableArray.forEach(bacASable -> importBacASableValues((JSONObject) bacASable));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void importBacASableValues(JSONObject bacASable) {
        this.initializationFile = (JSONObject) bacASable.get("values");

        int satisfaction = toIntExact((Long) this.initializationFile.get("satisfaction"));
        int satisfactionLoyalists = toIntExact((Long) this.initializationFile.get("satisfaction_loyalists"));
        int partisansPerFaction = toIntExact((Long) this.initializationFile.get("partisans_per_faction"));
        int industry = toIntExact((Long) this.initializationFile.get("industry_percentage"));
        int agriculture = toIntExact((Long) this.initializationFile.get("agriculture_percentage"));
        int treasury = toIntExact((Long) this.initializationFile.get("treasury"));

        //set up factions
        for (Faction faction : this.island.getFactions()) {
            faction.setNumberOfPartisans(toIntExact(partisansPerFaction));
            if (faction.getName() == "loyalists") {
                faction.setSatisfaction(toIntExact(satisfactionLoyalists));
            } else {
                faction.setSatisfaction(toIntExact(satisfaction));
            }
        }

        //set up indusrty, agriculture and treasury
        this.island.getIndustry().setPercentage(industry);
        this.island.getAgriculture().setPercentage(agriculture);
        this.island.getTreasury().setMoneyAvailable(treasury);
    }

    public int chooseDifficulty() {
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose the difficulty:");
        System.out.println("1: Easy\n2: Medium\n3: Hard");

        while (!correctAnswer) {
            this.difficulty = sc.nextInt();

            if (this.difficulty < 1 || this.difficulty > 3) {
                System.out.println("Choose between 1 and 3...");
            } else {
                correctAnswer = true;
            }
        }
        return this.difficulty;
    }

    public void setConfigurationFiles(int difficulty) {
        JSONParser jsonParser = new JSONParser();
        String fileName = "files/";
        switch (difficulty) {
            case 1:
                fileName += "easy.json";
                break;
            case 2:
                fileName += "medium.json";
                break;
            case 3:
                fileName += "hard.json";
        }

        try (FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject fileObject = (JSONObject) obj;


            parseFile(fileObject);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseFile(JSONObject file) {
        this.configurationFile = (JSONObject) file.get("values");
        this.scenario = (String) this.configurationFile.get("scenario_1");
        this.globalSatisfaction = toIntExact((Long) this.configurationFile.get("global_satisfaction_required"));
        this.events = (JSONArray) this.configurationFile.get("events");
        this.endOfYear = (JSONArray) this.configurationFile.get("end_of_the_year");
    }

    public void printScenario() {
        System.out.println(this.scenario);
    }

    public Event createEvent(int index) {
        JSONObject eventObject = (JSONObject) this.events.get(index);

        Event event = new Event();
        String title = (String) eventObject.get("event");
        event.setTitle(title);

        String season = (String) eventObject.get("season");
        event.setSeason(season);

        JSONArray JSONchoices = (JSONArray) eventObject.get("choices");

        ArrayList<Choice> choices = createEventChoices(JSONchoices);
        event.setChoices(choices);
        return event;
    }

    public ArrayList<Choice> createEventChoices(JSONArray JSONchoices) {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        for (int i = 0; i < JSONchoices.size(); i++) {
            JSONObject choiceObject = (JSONObject) JSONchoices.get(i);

            Choice choice = new Choice();
            String choiceTitle = (String) choiceObject.get("choice");
            choice.setTitle(choiceTitle);

            JSONObject positiveImpacts = (JSONObject) choiceObject.get("positive_impacts");

            choice.setPositiveImpacts(positiveImpacts);

            JSONObject negativeImpacts = (JSONObject) choiceObject.get("negative_impacts");
            choice.setNegativeImpacts(negativeImpacts);

            choices.add(choice);
        }
        return choices;
    }

    public Boolean isGlobalSatisfaction() {
        Boolean isGlobalSatisfaction = this.getIsland().getGlobalSatisfaction() >= this.globalSatisfaction;
        if (!isGlobalSatisfaction) {
            System.out.println("You loose... there is only " + this.getIsland().getGlobalSatisfaction() + "% of satisfaction");
        }
        return isGlobalSatisfaction;
    }

    public Boolean replay() {
        Boolean correctAnswer = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want replay ? yes/no");
            String choice = sc.next();
            while (!(choice.equals("yes") || choice.equals("no"))) {

                System.out.println("Please answer \"yes\" or \"no\"");
                choice = sc.next();
            }

        if(choice.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

}
