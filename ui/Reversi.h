#pragma once
#ifndef REVERSI_H
#define REVERSI_H

#include "Engine.h"
#include "Dice.h"
#include "Pair.h"
#include <set>



class Reversi : public Engine, public BoardInterface
{
	
public:


	Reversi();

	~Reversi();

	//void createDatabase();

	void initBoard(Board &board);

	void setCurrentPlayer(int);
	
	void fillPlayers(int num, string* arr);

	void fillPlayersComputer(int id);

	void setupGame();

	Status runGameLoop();

	int checkKilledPoints();

	Status aiEasyRunGameLoop();

	Status aiHardRunGameLoop();

	void teardownGame();

	Status updateState(int x, int y);

	bool canMove();

	bool isValidMove(int x, int y) ;

	Status flipPieces();

	vector<Pair>& flipPiecesAi();

	void SaveScores();

	void printBoard();

	void setBoard(int b[10][10]);

	int scores();

	string namePlayer();

	int idPlayer();

	Pair * coordinate;
	
	vector<Pair> PiecesToFlip; 

	vector<Pair> Kill; 
	
private:
	static const int sDirectionsTable[8][2];
	
};








#endif