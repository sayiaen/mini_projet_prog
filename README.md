# SNAKE

Welcome to Snake. A little game made in Scala with the FunGraphics Library


## About the Project
![GAMEPLAY.png](README_RESSOURCES/GAMEPLAY.png)


## Getting Started
Welcome in the menu, in this interface, you can choose to play or to select the difficulty or the levels in the settingS.

![MENU.png](README_RESSOURCES/MENU.png)
![SETTINGS.png](README_RESSOURCES/SETTINGS.png)


## Features

1. Change difficulty
2. Change level and save it in a file
3. Mystery box with random bonus or malus (Lot of Food, Speed Boost, Fog, Halving the Snake, )
4. Speed increase with time
5. Infinite play
6. Score calculation


## Usage

Use the WASD key to change the direction of the Snake


## How It Works

We split the project into several classes, grouped into the following categories:

1. Logic
2. Display
3. Keyboard and Mouse
5. Utilities

## Game

The Game class contains all the functions that connect the game logic with the user interface. It manages the global flow of the game using a state machine.

## GameLogic

The GameLogic class contains all the functions responsible for running the core logic of the game, independently of the user interface.

## Grid

The Grid class represents the game board. It is composed of characters that describe the type of each cell:

1. '_' for an empty cell
2. '#' for a wall
3. 'T' for the snake’s head
4. 'F' for food
5. '?' for the mystery box

## Chronometer

The Chronometer class is responsible for all timing mechanisms in the game, such as the snake’s movement speed, the duration of effects, and the random appearance and disappearance of the mystery box.

## Snake

The Snake class is responsible for managing the player. It uses a TTL (Time To Live) mechanism that is decreased after each update. The TTL value is exactly equal to the length of the snake. When the TTL of a cell reaches 0, the cell is converted back into an empty cell.


## Contact

Antoine Auugsburger
Eren Sayinta

## License

Free to use. Enjoy !
