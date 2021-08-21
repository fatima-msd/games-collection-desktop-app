#pragma once
#ifndef SNAKESANDLADDERS_H
#define SNAKESANDLADDERS_H

#include "Engine.h"
#include "Dice.h"
#include "Pair.h"

class SnakesAndLadders : public Engine, public BoardInterface
{
	// -id - 3 --> shows the index of below array the player will go
	Pair snakes_ladders[16] ;

        Pair arr[101] ;

        
	// -id - 11 --> shows the index of below array the player will go
	//Pair [8] = { };

	vector<Pair> coordinates;
        
        
        
public:
	Dice dice;
        
        int coordinateBoard;

	SnakesAndLadders();

	~SnakesAndLadders();

	void initBoard(Board &board);
	
	void fillPlayers(int num, string* str);

	void fillPlayersComputer();
        
        void setBoard(int b[10][10] );


	void setupGame();

	Status runGameLoop();

	void teardownGame(); 

	void setCurrentPlayer(int id);

	Status updateState(int x, int y);

	void SaveScores();

	bool canMove();

	void fillCoordinates();

	int idPlayer();
	
	//void createDatabase();
};

#endif // SNAKESANDLADDERS_H