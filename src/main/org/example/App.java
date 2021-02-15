package org.example;

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
    public static void main( String[] args )
    {
        Island kadIsland = new Island();
        Agriculture agriculture = new Agriculture();
        kadIsland.addFactions();
        kadIsland.setAgriculture(agriculture);

        System.out.println("avant : " + kadIsland.getNumberOfPartisans());

        kadIsland.randomPartisansElimination();

        for(Faction faction: kadIsland.getFactions()) {
            System.out.println(faction + ": "+ faction.getNumberOfPartisans());
        }
        System.out.println("after : " + kadIsland.getNumberOfPartisans());
    }

}
