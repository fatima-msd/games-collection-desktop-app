# games-collection-desktop app

## Description
This is a collection of two games: ***"Reversi"*** & ***"Snakes And Ladders"***.

It consists of two parts:
* Game engine written in C++
* User interface(UI) written in Java

The two parts are connected through *"JNI"* interface.


## Photos
In the following, you can see the design of running application:

Main Screen                |           Exiting & Saving             
:-------------------------:|:-------------------------:
<img src="https://github.com/salidotir/games-collection-desktop-app/blob/main/final%20desktop%20app%20photos/1.jpg" height="600">  |  <img src="https://github.com/salidotir/games-collection-desktop-app/blob/main/final%20desktop%20app%20photos/4.jpg" height="600">

Player info           |            Game settings       
:-------------------------:|:-------------------------:
<img src="https://github.com/salidotir/games-collection-desktop-app/blob/main/final%20desktop%20app%20photos/3.jpg" height="600">  |  <img src="https://github.com/salidotir/games-collection-desktop-app/blob/main/final%20desktop%20app%20photos/2.jpg" height="600">

Reversi game board           |            Snakes & Ladders game board       
:-------------------------:|:-------------------------:
<img src="" height="600">  |  <img src="" height="600">


## Usage
TO run the application, execute below commands in terminal
### Linux:
```bash
cd ui
javac *.java
cd ..
javah ui.SnakeAndLaddersBoard
javah ui.ReversiBoard
cd ui 
g++ -std=c++11 -shared -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux  -fPIC -I$JAVA_INC -I$JAVA_INC/linux *.cpp -o Reversi.so
g++ -std=c++11 -shared -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux  -fPIC -I$JAVA_INC -I$JAVA_INC/linux *.cpp -o SnakesAndLadders.so
cd ..
java ui.UI
```
