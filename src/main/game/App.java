package game;
import year.Year;
import java.util.Scanner;


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

        Boolean correctAnswer = false;
        Boolean replay = true;
        while(parameters.isGlobalSatisfaction() && replay) {
            year.playSeasons();
            if(!parameters.isGlobalSatisfaction()) {
                Scanner sc = new Scanner(System.in);
                System.out.println("tu veux rejouer ? 0 ou 1 : oui");
                int choice = 0;
                while (!correctAnswer) {
                    choice = sc.nextInt();
                    System.out.println("je rentre dans le while");
                    if(choice != 0 && choice != 1) {

                        System.out.println("0 ou 1");
                    } else {
                        correctAnswer = true;
                    }
                }
                if(choice == 0) {
                    replay = false;
                }

            }
        }

    }

}
