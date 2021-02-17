package game;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GameConfiguration {

    private int difficulty;
    private JSONObject file;

    public GameConfiguration() {
    }

    public JSONObject getFile() {
        return file;
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

    public void setConfigurationFile(int difficulty) {
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

            JSONArray niveauList = (JSONArray) obj;
            System.out.println(niveauList);

            //Iterate over employee array
            niveauList.forEach( niveau -> parseNiveauObject( (JSONObject) niveau ) );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseNiveauObject(JSONObject niveau)
    {
        this.file = (JSONObject) niveau.get("bacASable");

        Long satisfaction = (Long) this.file.get("satisfaction");
        System.out.println(satisfaction);

        Long satisfactionLoyalists = (Long) this.file.get("satisfaction_loyalists");
        System.out.println(satisfactionLoyalists);
    }
}
