package game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public static void main( String[] args )
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("files/init.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray niveauList = (JSONArray) obj;
            System.out.println(niveauList);

            //Iterate over employee array
            niveauList.forEach( niveau -> parseEmployeeObject( (JSONObject) niveau ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseEmployeeObject(JSONObject niveau)
    {
        JSONObject niveauObject = (JSONObject) niveau.get("bacASable");

        Long satisfaction = (Long) niveauObject.get("satisfaction");
        System.out.println(satisfaction);

        Long satisfactionLoyalists = (Long) niveauObject.get("satisfaction_loyalists");
        System.out.println(satisfactionLoyalists);

    }

}
