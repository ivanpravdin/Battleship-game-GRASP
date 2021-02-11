# Battleship game GRASP
Battleship game GRASP for APCS. Game is coded on LIBGDX Engine. Game consists of 4 screens. When you start the game, you will see the main menu screen:  
  
![Main menu](https://i.ibb.co/wZnL27n/screen1.png)  
  
After starting the game, the first player will be able to place their ships. By default, each player has 4 ships with length 2, 3, 4, and 5 squares. Click left mouse button to place ship vertically and right mouse button to place ship horizontally. If placement is invalid, ship will not be placed.  
  
![Placing screen](https://i.ibb.co/bN2MDJ6/screen2.png)  
  
After all the ships were placed, game will start automatically. Click on any square to reveal what's there. If square is green, your rival's ships is located there, but you did not drown it all. If square is red, you drowned entire rival's ship. If square is gray, there's nothing. If you missed, turn is given to your rival.  
  
![Playing screen](https://i.ibb.co/VpJmFXP/screen4.png)  
   
Player who drowned all of the ships of their rival wins.  
  
![Winning screen](https://i.ibb.co/0ssgJW7/screen5.png)   

# Code  
Game consists of 6 classes and 1 additional class for Desktop launcher.
Classes included:
- BattleshipGame.java (Main class)  
- MainMenuScreen.java (Main menu sreen class)  
- GameScreen.java (Game screen class)  
- Field.java (Class for creating Field object)  
- Player.java (Class for creating Player object)  
- Ship.java (Class for creating Ship class)  
  
- DesktopLauncher.java (LINGDX generated class for Desktop Launcher)  
