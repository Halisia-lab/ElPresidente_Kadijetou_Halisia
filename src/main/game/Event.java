package game;

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



}
