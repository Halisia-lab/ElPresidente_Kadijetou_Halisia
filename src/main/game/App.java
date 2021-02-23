package game;
import factions.Faction;
import islandcaracteristics.Island;
import year.Year;
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



        //Set the game parameters
        GameConfiguration parameters = new GameConfiguration();
        parameters.setConfigurationFiles(parameters.chooseDifficulty());
        parameters.setBacASableMode();

        Year year = new Year(parameters);

        year.getGame().printScenario();

        //Start the game for 2 years
        while(parameters.isGlobalSatisfaction()) {
            year.playSeasons();
        }
    }


}
