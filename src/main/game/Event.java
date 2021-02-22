package game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import year.SeasonEnum;

public class Event {
    private String title;
    private String season;
    private JSONArray choices;

    public Event() {

    }

    public String getTitle() {
        return title;
    }

    public String getSeason() {
        return season;
    }

    public JSONArray getChoices() {
        return choices;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setChoices(JSONArray choices) {
        this.choices = choices;
    }


}
