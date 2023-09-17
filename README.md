# ProgrammingPj
El proyectos consiste en realizar un juego tradicional, Sokoban:
Se intenta practicar y recordar el uso:
        * Junit
        * SonarQube
        * Maven
Ademas del uso de principios de:
        * Architectural Pattern model, view and controller(MVC)

# Description
This project implements a program for playing **Sokoban**, a jigsaw puzzle whose goal is to move boxes from their original place to a goal place in a small **warehouse**.

**IMPLEMENTATIONS**

* The game is composed of several items, the **Box**, the **Wall**, the **Warehouse Man**, the **Floor** and a **Goal Position**.

* The **Board** is divided in squares, such that each square contains one of the following previous items.

* Each items contains movements except the **Wall** and **Floor**.

* The **Level** contains the movements on the board, as well as redo and undo movements.

* The **operations** contains the menu actions as a **Start a New Game**, **Restart level**, **Undo**, **Redo**, **SaveGame**, **LoadGame**, **PassLevel**.

* The **LoaderGameFile** Load a game which has been previously saved.

* The **SaverGameFile** Saves the current state of the game,It creates a file where the data of the game will be saved, the data to be saved are the board's dimension, the board's elements, 
  the level's score, the level's name and the game's total score.

* The **Game object** contains the path argument must exist in the system and must contain the level files to create the levels.The levelName must be the name of an existing level.
