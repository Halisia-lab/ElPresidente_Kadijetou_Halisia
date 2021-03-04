package game;

import factions.Faction;
import islandcaracteristics.*;

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

    public void setPositiveImpacts(JSONObject positiveImpacts) {
        this.positiveImpacts = positiveImpacts;
    }

    public void setNegativeImpacts(JSONObject negativeImpacts) {
        this.negativeImpacts = negativeImpacts;
    }

    public void applyImpacts(Island island) {
        Iterable<String> keysPositive = this.positiveImpacts.keySet();
        for(String key: keysPositive) {
            if(key.equals("numberOfPartisans")) {
                island.randomBirthsRepartition(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
        Iterable<String> keysNegative = this.negativeImpacts.keySet();
        for(String key: keysNegative) {
            if(key.equals("numberOfPartisans")) {
                island.randomPartisansElimination(toIntExact((Long) this.negativeImpacts.get(key)));
            }
        }
    }

    public void applyImpacts(Faction faction) {
        Iterable<String> keysPositive = this.positiveImpacts.keySet();
        for(String key: keysPositive) {
            if(faction.getName().equals(key)) {
                faction.increaseSatisfaction(toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }

        Iterable<String> keysNegative = this.negativeImpacts.keySet();
        for(String key: keysNegative) {
            if(faction.getName().equals(key)) {
                faction.decreaseSatisfaction(toIntExact((Long) this.negativeImpacts.get(key)));
            }
        }
    }

    public void applyImpacts(Industry industry) {
        Iterable<String> keysPositive = this.positiveImpacts.keySet();
        for(String key: keysPositive) {
            if(key.equals("industry")) {
            industry.setPercentage(industry.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
        }
    }
        Iterable<String> keysNegative = this.negativeImpacts.keySet();
        for(String key: keysNegative) {
            if(key.equals("industry")) {
                industry.setPercentage(industry.getPercentage() - toIntExact((Long) this.negativeImpacts.get(key)));
            }
        }
    }

    public void applyImpacts(Agriculture agriculture) {
        Iterable<String> keysPositive = this.positiveImpacts.keySet();
        for(String key: keysPositive) {
            if(key.equals("industry")) {
                agriculture.setPercentage(agriculture.getPercentage() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
        Iterable<String> keysNegative = this.negativeImpacts.keySet();
        for(String key: keysNegative) {
            if(key.equals("industry")) {
                agriculture.setPercentage(agriculture.getPercentage() - toIntExact((Long) this.negativeImpacts.get(key)));
            }
        }
    }

    public void applyImpacts(Treasury treasury) {
        Iterable<String> keysPositive = this.positiveImpacts.keySet();
        for(String key: keysPositive) {
            if(key.equals("treasury")) {
                treasury.setMoneyAvailable(treasury.getMoneyAvailable() + toIntExact((Long) this.positiveImpacts.get(key)));
            }
        }
        Iterable<String> keysNegative = this.negativeImpacts.keySet();
        for(String key: keysNegative) {
            if(key.equals("treasury")) {
                treasury.setMoneyAvailable(treasury.getMoneyAvailable() - toIntExact((Long) this.negativeImpacts.get(key)));
            }
        }
    }
}
