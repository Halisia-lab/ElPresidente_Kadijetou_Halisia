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

import static java.lang.Math.toIntExact;


public class GameConfiguration {

    private int difficulty;
    private int mode; //sandbox = 1 - scenario = 2
    private Island island;
    private JSONObject initializationFile;
    private JSONObject configurationFile;
    private String scenario;
    private JSONArray events;
    private int globalSatisfaction;
    private JSONArray endOfYear;

    public GameConfiguration() {
        this.island = new Island();
    }

    public Island getIsland() {
        return island;
    }

    public JSONArray getEvents() {
        return events;
    }

    public int getMode() {
        return this.mode;
    }

    public void initialization() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("files/default_values.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray default_values = (JSONArray) obj;

            default_values.forEach(values -> importDefaultValues((JSONObject) values));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void importDefaultValues(JSONObject values) {
        this.initializationFile = (JSONObject) values.get("values");

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

    public int chooseMode() {
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose the mode :");
        System.out.println("1: Sandbox\n2: Scenario\n");

        while (!correctAnswer) {
            this.mode = sc.nextInt();

            if (this.mode < 1 || this.mode > 2) {
                System.out.println("Choose between 1 and 2...");
            } else {
                correctAnswer = true;
            }
        }
        return this.mode;
    }

    public void setConfigurationFiles(int difficulty, int mode) {
        JSONParser jsonParser = new JSONParser();
        String modeName = "";
        if(mode == 1) {
            modeName = "sandbox/";
        } else {
            modeName = "scenario/";
        }
        String fileName = "files/" + modeName;
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

    public Event createEvent(int index) { //to get the event at the position "index" in the array and set its parameters
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

    public ArrayList<Choice> createEventChoices(JSONArray JSONchoices) { //to set an event's choices and set their parameters
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
}
