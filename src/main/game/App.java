package game;
import factions.Faction;
import islandcaracteristics.Island;
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

        //Create the Island
        Island island = new Island();

        //Set the game parameters
        GameConfiguration parameters = new GameConfiguration(island);
        //parameters.setConfigurationFiles(parameters.chooseDifficulty());
        parameters.setBacASableMode();

        if(!island.areFoodUnitsEnough()) {
            System.out.println(island.randomPartisansElimination());
        }

        System.out.println("partisans before : "+island.getNumberOfPartisans());

        if(island.isAgricultureEnough()) {
            System.out.println(island.randomBirthsRepartition());
        }

        island.organizeABribe(island.getCommunists());
        for(Faction faction: island.getFactions()) {
            System.out.println(faction.getName() + ": satisfaction= "+faction.getSatisfaction()+", nbpartisans: " + faction.getNumberOfPartisans());
        }

    }


}
