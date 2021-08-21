#pragma once
#ifndef STATUS_H
#define STATUS_H

#include<string>
#include<string.h>
using namespace std;

enum Status {
	SUCCESS,			// valid move, piece successfully placed
	OUT_OF_BOUNDS,		// invalid move, position is not on the board
	POSITION_FILLED,	// invalid move, position is already filled
	INVALID_MOVE,		// invalid move, position would not flip any of the opponent's pieces
	CANNOT_MOVE,		// current player cannot move
	QUIT,				// quit application
	FINDING_MOVE,		// searching for a computer player's move
	WIN,				// win
	KILL,				// killed frenzy ! kills the other player
	AWARD,				// if dice == 6
	BITE,				// snakes
	CLIMB				// ladders
};

int toStatus(char * c);
char* toString(Status s);

#endif //STATUS_H