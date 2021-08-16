#include "Pair.h"



Pair::Pair(int x1, int y1)
{
	x = x1;
	y = y1;
}


Pair::~Pair()
{
}


Pair::Pair()
{
	Pair(0,0);
}

void Pair::setX(int value)
{
	x = value;
}

int Pair::getX() const
{
	return x;
}

void Pair::setY(int value) 
{
	y = value;
}

int Pair::getY() const
{
	return y;
}
