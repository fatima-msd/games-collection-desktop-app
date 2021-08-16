#pragma once
#ifndef PLAYER_H
#define PLAYER_H

#include <string>

using namespace std;

class Player
{
public:
	Player();

	Player(int id, string name);

	~Player();

	string getUserName() const;

	void setUserName(string name);

	int getId() const;

	void setId(int value);

	int getScore() const;

	void setScore(int value);

private:
	string userName;
	int id;
	int score;
};

#endif // PLAYER_H
