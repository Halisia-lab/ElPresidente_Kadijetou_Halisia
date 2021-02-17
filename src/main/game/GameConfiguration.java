package game;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

import factions.Faction;
import factions.Loyalist;
import islandcaracteristics.Island;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.toIntExact;


public class GameConfiguration {

    private int difficulty;
    private JSONObject initializationFile;
    private JSONObject configurationFile;
    private Island island;

    public GameConfiguration(Island island) {
        this.island = island;
    }

    public JSONObject getInitializationFileFile() {
        return initializationFile;
    }

    public JSONObject getConfigurationFile() {
        return configurationFile;
    }

    public Island getIsland() {
        return island;
    }

    public int chooseDifficulty() {
        Boolean correctAnswer = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose the difficulty:");
        System.out.println("0: \"Bac à sable\"\n 1: Easy\n2: Medium\n3: Hard");

        while(!correctAnswer) {
            this.difficulty = sc.nextInt();
            if(this.difficulty < 0 || this.difficulty > 3) {
                System.out.println("Please choose between 0 and 3...");
            } else {
                correctAnswer = true;
            }
        }

        System.out.println("You chose : " + this.difficulty);
        sc.close();
        return this.difficulty;
    }


    public void setBacASableMode() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("files/bacasable.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray bacASableArray = (JSONArray) obj;

            //Iterate over employee array
            bacASableArray.forEach( bacASable -> importBacASableValues( (JSONObject) bacASable ));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void importBacASableValues(JSONObject bacASable)
    {
        this.initializationFile = (JSONObject) bacASable.get("values");

        Long satisfaction = (Long) this.initializationFile.get("satisfaction");
        Long satisfactionLoyalists = (Long) this.initializationFile.get("satisfaction_loyalists");
        Long partisansPerFaction = (Long) this.initializationFile.get("partisans_per_faction");
        Long industry = (Long) this.initializationFile.get("industry_percentage");
        Long agriculture = (Long) this.initializationFile.get("agriculture_percentage");
        Long treasury = (Long) this.initializationFile.get("treasury");

        //set up factions
        for(Faction faction:this.island.getFactions()) {
            faction.setNumberOfPartisans(toIntExact(partisansPerFaction));
            if(faction.getName() == "loyalists" ) {
                faction.setSatisfaction(toIntExact(satisfactionLoyalists));
            } else {System.out.println("****"+faction.getName());
                faction.setSatisfaction(toIntExact(satisfaction));
            }

        }

        //set up indusrty, agriculture and treasury
        this.island.getIndustry().setPercentage(toIntExact(industry));
        this.island.getAgriculture().setPercentage(toIntExact(agriculture));
        this.island.getTreasury().setMoneyAvailable(treasury);
        System.out.println("treasury = "+ treasury+", agriculture = "+ agriculture);
    }



    public void setConfigurationFiles(int difficulty) {
        JSONParser jsonParser = new JSONParser();
        String fileName = "files/";
        switch (difficulty) {
            case 0:
                fileName += "bacasable.json";
                break;
            case 1:
                fileName += "easy.json";
                break;
            case 2:
                fileName += "medium.json";
                break;
            case 3:
                fileName += "hard.json";
        }

        try (FileReader reader = new FileReader(fileName))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray fileArray = (JSONArray) obj;
            System.out.println(fileArray);

            //Iterate over employee array
            fileArray.forEach( initialization -> parseDifficultyObject( (JSONObject) initialization ));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseDifficultyObject(JSONObject difficulty)
    {
        this.configurationFile = (JSONObject) difficulty.get("bacASable");

        Long satisfaction = (Long) this.configurationFile.get("satisfaction");
        System.out.println(satisfaction);

        Long satisfactionLoyalists = (Long) this.configurationFile.get("satisfaction_loyalists");
        System.out.println(satisfactionLoyalists);
    }
}
