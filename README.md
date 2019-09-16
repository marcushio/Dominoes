Dominoes - This is a simple dominoes game. It has a console version and a GUI version. 

____How to open: _____
1. Console Version
   -use the command line to launch. I've had no luck outside of it. 
   "java -jar dominoesConsole_marcustrujillo" should open it in the command line. 
2. GUI Version
   -This version works by double clicking the jar file, it should open and you can 
   proceed with gameplay 
   
Compiling/Starting
Both have public static void main in class called Main
   
____How to Play ____
1. Console Version 
The player's hand is indexed starting at 0. User will be prompted asking which tile to use. 
Choose the tile you want to play by typing in the index of the desired tile and pressing enter. 
Then the user will be prompted to type in the number on the tile that they wish to utilize. Then 
this tile will be matched to the exposed tile that matches the number the user selected. 
Wildcards will play automatically on the right side of the board. 

2. GUI Version 
User selects which tile and the side of the domino they wnat to play by clicking on that domino at 
the side they wish to use. Once the user is happy with their choice, the then click on the left or right 
button depending on which side they want to play on. To summarize, select which domino and side by clicking, 
then click the left button if you want to play on the left side of the board, or right if you want to play it
on the right side of the board. You can also pass if you have no moves and the boneyard is empty. Simply click
the pass button if this is the case. 

____BUGS/ISSUES___

There is no protection from improper input. There was no specification in the instructions, 
so this feature was prioritized very lowly. 

In the gui version the board display is kind of hard to track because it shifts as the board is updated. 
In particular, if a tile is added to the left side of the board. The order does hold, and the tiles are 
placed correctly, it just kind of jumps around a little. 

Components not fixed in place. Things like buttons and displays adjust dynamically, as the board expands, 
the "RIGHT" button moves over. functionality is fine, it just isn't very aesthetically pleasing. Same happens if 
the computer hasn't drawn yet, things shift up into the territory that their hand used to occupy. 


