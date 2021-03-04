package game;
import year.Year;

public class App 
{
    public static void main( String[] args ) {

        //Set the game parameters
        GameConfiguration parameters = new GameConfiguration();
        parameters.setConfigurationFiles(parameters.chooseDifficulty(), parameters.chooseMode());
        parameters.initialization();

        Year year = new Year(parameters);
        year.getGame().printScenario();

        //Start the game until satisfaction is under difficulty.satisfaction
        while(parameters.isGlobalSatisfaction()) {
            year.playSeasons();
        }
    }

}
