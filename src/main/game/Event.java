package game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import year.SeasonEnum;

import java.util.ArrayList;

public class Event {
    private String title;
    private String season;
    private ArrayList<Choice> choices;

    public Event() {

    }

    public String getTitle() {
        return title;
    }

    public String getSeason() {
        return season;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }



    /*public void createChoices() {
        for(Object choiceObject: this.choices) {
            Choice choice = new Choice();
            String title = (String) this.choices.get("choice");
        }
    }*/


}
