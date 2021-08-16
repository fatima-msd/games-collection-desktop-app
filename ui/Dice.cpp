#include "Dice.h"
using namespace std;


Dice::Dice()
{
}


Dice::~Dice()
{
}

int Dice::getValue() const
{
	return value;
}

int Dice::setValue(int value) 
{
    this->value = value;
}

int Dice::dice()
{
	srand(time(NULL));
	int random = abs((rand()*prime) % 6) + 1;
	cout<<"random "<<random << endl;
	value = random;
	return random;
}
