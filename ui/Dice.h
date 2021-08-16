#pragma once
#include <stdlib.h>
#include <iostream>
using namespace std;
#define prime 443

class Dice
{
	int value;
public:
	Dice();
	~Dice();

	int getValue() const;
        
        int setValue(int value);
        
	int dice();
};

