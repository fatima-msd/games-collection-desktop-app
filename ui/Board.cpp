#include "Board.h"



Board::Board()
{
}


Board::~Board()
{
}

void Board::setElement(int x, int y, int value)
{
	board[x][y] = value;
}

int Board::getElement(int x, int y) const
{
	return board[x][y];
}