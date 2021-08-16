
#include "Engine.h"

// void createFile( char s[1000]){
//     char address[1000];
// 	strcpy(address,"touch ");
// 	strcat(address,s);
// 	system(address);
// }

Engine::Engine()
{
}


Engine::~Engine()
{
}





bool Engine::isOnBoard(int x, int y) const
{
	if (x >= lengthBoard || y >= lengthBoard || x < 0 || y < 0)
	{
		return false;
	}
	return true;
}


int Engine::isOpen(int x, int y) const
{
	return board.getElement(x, y);
}


int Engine::findPlayerInPlayers(Player currentPlayer)
{
	for (auto iter = players.begin(); iter != players.end(); iter++)
	{
		if (iter->getId() == currentPlayer.getId())
		{
			return distance(players.begin(), iter);
		}
	}
	return -1;
}


void Engine::togglePlayer()
{
	// toggles based on player's id
	int turn = findPlayerInPlayers(*currentPlayer);
	
	if (turn < players.size()-1)
	{
		// next player's turn
		currentPlayer=&players[turn+1];

		
	}
	else
	{
		// first player's turn
		currentPlayer=&players[0];
	}
}


int Engine::getPosition(int x, int y) const
{
	if (isOnBoard(x, y))
	{
		return board.getElement(x, y);
	}

	return -1;
}


void Engine::setPosition(int x, int y, int value)
{
	if (isOnBoard(x, y))
	{
		board.setElement(x, y, value);
	}
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Player * Engine::getPlayer(int id) const
{
	return nullptr;
}


void Engine::setPlayer(Player * player, int id)
{
	
}



// void saveGame(string address){
	
// }

// void Engine::loadGame(string address){

// }
	
// void Engine::saveScore(string address){

// }

// void Engine::loadScore(string address){

// }
