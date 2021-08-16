#pragma once
#ifndef BOARD_H
#define BORAD_H

#define lengthBoard 10
//const int lengthBoard = 10;

class Board
{
public:
	Board();
	~Board();

	void setElement(int x, int y, int value);

	int getElement(int x, int y) const;

	int board[lengthBoard][lengthBoard];
};

class BoardInterface 
{
public:
	virtual void initBoard(Board &board) = 0;
};

#endif // BOARD_H