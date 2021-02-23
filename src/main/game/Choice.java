package game;

import factions.Faction;
import islandcaracteristics.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static java.lang.Math.toIntExact;

public class Choice {
    private String title;
    private JSONObject positiveImpacts;
    private JSONObject negativeImpacts;

    public Choice() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject getPositiveImpacts() {
        return positiveImpacts;
    }

    public void setPositiveImpacts(JSONObject positiveImpacts) {
        this.positiveImpacts = positiveImpacts;
    }

    public JSONObject getNegativeImpacts() {
        return negativeImpacts;
    }

    public void setNegativeImpacts(JSONObject negativeImpacts) {
        this.negativeImpacts = negativeImpacts;
    }

    public void setPositiveImpacts(Island island) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("numberOfPartisans")) {
                island.randomBirthsRepartition(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setPositiveImpacts(Faction faction) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(faction.getName().equals(key)) {
                faction.increaseSatisfaction(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setPositiveImpacts(Industry industry) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("industry")) {
                industry.setPercentage(industry.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setPositiveImpacts(Agriculture agriculture) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("industry")) {
                agriculture.setPercentage(agriculture.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setPositiveImpacts(Treasury treasury) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("treasury")) {
                treasury.setMoneyAvailable(treasury.getMoneyAvailable() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setNegativeImpacts(Faction faction) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(faction.getName().equals(key)) {
                faction.increaseSatisfaction(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setNegativeImpacts(Industry industry) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("industry")) {
                industry.setPercentage(industry.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setNegativeImpacts(Agriculture agriculture) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("industry")) {
                agriculture.setPercentage(agriculture.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setNegativeImpacts(Treasury treasury) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("treasury")) {
                treasury.setMoneyAvailable(treasury.getMoneyAvailable() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

    public void setNegativeImpacts(Island island) {
        Iterable<String> keys = this.positiveImpacts.keySet();
        for(String key: keys) {
            if(key.equals("numberOfPartisans")) {
                island.randomBirthsRepartition(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
    }

}
