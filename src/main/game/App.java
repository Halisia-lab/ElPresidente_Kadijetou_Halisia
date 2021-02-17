package game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import factions.Faction;
import islandcaracteristics.Agriculture;
import islandcaracteristics.Industry;
import islandcaracteristics.Island;
import islandcaracteristics.Treasury;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
* Une classe par faction => ok
* Une classe abstraite pour saison
* Fichier configuration JSON => ?
* une classe pour Industrie, Agriculture Tresorerie
* fonction initialisation pour valeurs default
* une classe pour la difficulté avec attribut enumeration
* constante seuil
* classe partisan avec attribut unité pour chaque
*
*
* LUNDI SOIR : Création des classes =>ok
* MERCREDI SOIR: classe abstraite pour saison...
* SAMEDI SOIR: valeur par defaut + class partisan + test
* MARDI SOIR : solution pour diffficulté (fichiers)
*
*
* */


public class App 
{
    public static void main( String[] args ) {
        Island island = new Island();
        island.addFactions();
        island.setAgriculture(new Agriculture());
        island.setIndustry(new Industry());
        island.setTreasury(new Treasury());
        //set the game parameters
        GameConfiguration parameters = new GameConfiguration(island);
        //parameters.setConfigurationFiles(parameters.chooseDifficulty());
        parameters.setBacASableMode();

        for(Faction faction:island.getFactions()) {
            System.out.println(faction.getSatisfaction());
        }
        System.out.println(island.getNumberOfPartisans());
    }


}
