# ElPresidente

El presidente is a small game which looks like Tropico or Reigns. You have the role of the president of an Island, and you have to make some choices during the year.

## Getting started 

![event](https://user-images.githubusercontent.com/63605419/110221207-2b02d980-7ecb-11eb-9d85-22bc1f0f84db.jpg)    </br></br>
When you run the game, it is played on a terminal and you only have to use the keyboard. After choosing the difficulty and the game mode, you can start.</br></br> 
All the Island details will be shown at each season, so that you can see the treasury or all satisfactions for example. Every answer has to be a number, if you enter a wrong number, the game will ask you to retry.    
It continues until the end of the year, and then a new year will start until you lose...    
You lose when the average satisfaction is under the satisfaction required. In the easy mode, you need more than 10%, in the medium one, more than 25%, and in the hard one, more than 40%. 

## Code description 
Just below is an arborescence of the classes that are used in the project.</br></br>
![factions](https://user-images.githubusercontent.com/63605419/110224774-d79a8680-7ede-11eb-9e51-f273ebca6236.png)  
A year has its GameConfiguration, to set the mode and difficulty. Then a GameConfiguration has its Island, we suppose that if the game is improved, it will be maybe possible to have more than one island to play.  

The project is divided into 4 packages : Game - Faction - Island Caracteristics - Year  </br></br>
![classes](https://user-images.githubusercontent.com/63605419/110224092-f39b2980-7ed8-11eb-80c8-fe0e7d579ab2.jpg)


### Game 

This is where we find the App file, and the classes related to the functionement of the game.

#### GameConfiguration
This is where we set all parameters of the game.  
Configuration files are located in the "files" folder. The path is used to call them when the user enter which mode or which difficulty he wants.  
This class does the link between the mode/difficulty and the events of the game.    
Files are parsed here and events are created whith the function createEvent which return an Event Object.
#### Event 
Each event from the configuration file is converted in a Event object to have a better usage. An event has an Array of choices that are objects too.
#### Choice
Each choice of an event is converted in a Choice Object, with its impacts. For positives impacts, all functions whith a gain, an increasation will be called.  
- for the number of partisans, this is the function "randomBirthRepartition" that adds partisansin the Island   
- for the satisfactions, the function "increaseSatisfaction"  
- for the industry, agriculture or treasury percentage, the function "setPercentage" with += affectation.  
</br></br>This is the same functionement for the negative impacts.


### Factions

Here are all the classes for each faction. An abstract class is used as a parent of this classes.

### IslandCaracteristics

All informations about the island is reunited in this package.

#### Island
This class does the link between the activity areas, the treasury and the factions. We can get here the number partisans in all faction reunited, so that we can get the global satisfaction.  
This is also where the checking are done : foodunits, agriculture, if we need to eliminate partisans, etc ..

#### ActivityArea 
This is an interfact which is implemented by Industry and Agriculture. Both have a percentage and return us the ressources.

#### Industry 
 
 This class gives us the money given by the indusrty per year.
 
#### Agriculture 

This one gives us the amount of food units given by the agriculture.

#### Treasury

The amount of the treasury is calculated here.

### Year

Seasons events, end of year actions, impact of user choices are managed here. 

#### Year 

With the function "playSeasons" this class calls the functions chronologically to have the printings, the repercussions after each choice. This is repeated for each seasons and until the end of the game, when the user lose.

#### SeasonEnum
A simple enumeration with the 4 seasons. 
