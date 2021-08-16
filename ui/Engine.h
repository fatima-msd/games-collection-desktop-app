#pragma once
#ifndef ENGINE_H
#define ENGINE_H

#include <vector>
#include <iostream>
#include <string>
#include <string.h>
#include <stdlib.h>
// #include <dirent.h>
// #include <sys/stat.h>
#include <math.h>
#include "Player.h"
#include "Board.h"
#include "Status.h"
#include "HumanPlayer.h"
#include "CopmputerPlayer.h"

using namespace std;

struct Game{
	Player * players;
	Board & board;
};

class Engine
{
public:
	Engine();

	~Engine();

	// start a new game and starts the game loop.
	

	virtual Player* getPlayer(int id = 0) const;

	// Updates a player object pointer, usually when changing player type.
	virtual void setPlayer(Player* player, int id = 0);

	//virtual bool isValidMove(int x, int y, bool isCheck = false) = 0;

	// Determines if the supplied position (x/y indexes) is within the game board.
	virtual bool isOnBoard(int x, int y) const;

	// Determines if the supplied position (x/y indexes) is open (is empty and not already taken).
	virtual int isOpen(int x, int y) const;

	// Returns the value of the specified board position.
	virtual int getPosition(int x, int y) const;

public:
	// Initializes a new game, including board state and current player.
	virtual void setupGame() = 0;

	//virtual void createDatabase() = 0;

	// runGameLoop
	virtual Status runGameLoop() = 0;

	// teardownGame
	// Calls view.teardownGame.
	virtual void teardownGame() = 0;

	// Updates game state based on input.
	// Returns a Status enum value representing update status.
	virtual Status updateState(int x, int y) = 0;

	// Determines if the current player can make a legal move.
	virtual bool canMove() = 0;

	// Toggles currentPlayer between Player.
	virtual void togglePlayer();

	// Calculates each player's current score, and sets the score on each player object
	//virtual void updateScores(bool isGameOver = false) = 0;

	// Sets the value of the supplied position (x/y indexes) to the supplied value.
	virtual void setPosition(int x, int y, int value);

	// virtual void saveGame(string);

	// virtual void loadGame(string);
	
	// virtual void saveScore(string);

	// virtual void loadScore(string);

	Board board;

protected:
	int findPlayerInPlayers(Player currentPlayer);

	
	vector<Player> players;
	Player *currentPlayer;
	
};

#endif //ENGINE_H
