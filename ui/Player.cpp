#include "Player.h"

Player::Player()
{
}

Player::Player(int id, string name)
{
	setId(id);
	setUserName(name);
	setScore(0);
}


Player::~Player()
{
}

string Player::getUserName() const
{
	return userName;
}

void Player::setUserName(string name)
{
	userName = name;
}

int Player::getId() const
{
	return id;
}

void Player::setId(int value)
{
	id = value;
}

int Player::getScore() const
{
	return score;
}

void Player::setScore(int value)
{
	score = value;
}
