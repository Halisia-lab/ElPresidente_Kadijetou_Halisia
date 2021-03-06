# ElPresidente

El presidente is a small game which looks like Tropico or Reigns. You have the role of the presfeident of an Island, and you have to make some choices during the year.

## Getting started 

![event](https://user-images.githubusercontent.com/63605419/110221207-2b02d980-7ecb-11eb-9d85-22bc1f0f84db.jpg)    </br></br>
When you run the game, it is played on a terminal and you only have to use the keyboard. After choosing the difficulty and the game mode, you can start.</br></br> 
All the Island details will be shown at each season, so that you can see the treasury or all satisfactions for example. Every answer has to be a number, if you enter a wrong number, the game will ask you to retry.    
It continues until the end of the year, and then a new year will start until you lose...    
You lose when the average satisfaction is under the satisfaction required. In the easy mode, you need more than 10%, in the medium one, more than 25%, and in the hard one, more than 40%. 

## Code description 
The project is divided into 4 packages : Game - Faction - Island Caracteristics - Year  

### Game 

This is where we find the App file, and the classes related to the functionement of the game.

#### GameConfiguration
This is where we set all parameters of the game. Events are created from the different files, and all the corresponding choices. 
#### Event 
Each event from the configuration file is converted in a Event object to have a better usage.
#### Choice
Each choice of an event is converted in a Choice Object, with its impacts.
