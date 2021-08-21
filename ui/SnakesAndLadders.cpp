#include "SnakesAndLadders.h"


SnakesAndLadders::SnakesAndLadders()
{
	snakes_ladders[0] = *(new Pair(17,7));
	snakes_ladders[1] = *new Pair(54,34);
	snakes_ladders[2] = *new Pair(62,19);
	snakes_ladders[3] = *new Pair(64, 60);
	snakes_ladders[4] = *new Pair(93,73);
	snakes_ladders[5] = *new Pair(87,24);
	snakes_ladders[6] = *new Pair(95,75);
	snakes_ladders[7] = *new  Pair(98,79);
	snakes_ladders[8] = *new Pair(1,38);
	snakes_ladders[9] = *new Pair(4,14);
	snakes_ladders[10] = *new Pair(9,31);
	snakes_ladders[11] = *new Pair(21,42);
	snakes_ladders[12] = *new Pair(28,84);
	snakes_ladders[13] = *new Pair(51,67);
	snakes_ladders[14] = *new Pair(71,91);
	snakes_ladders[15] = *new Pair(80,100);

	fillCoordinates();
        
}

SnakesAndLadders::~SnakesAndLadders()
{
}

int SnakesAndLadders::idPlayer(){
	return currentPlayer->getId();
}

void SnakesAndLadders::setCurrentPlayer(int id){
	currentPlayer = &players[id];
}

void SnakesAndLadders::fillPlayers(int num, string * str)
{
	Pair pair(-1, 0);
	for (int i = 0; i < num; i++)
	{
		Player* p = new Player(i + 1, str[i]);
		players.push_back(*p);
		//delete[] p;
		coordinates.push_back(pair);
	}
}

void SnakesAndLadders::fillPlayersComputer()
{
	Pair pair(-1, 0);
	
	Player* p = new Player(-2, "AI");
	players.push_back(*p);
	//delete[] p;
	coordinates.push_back(pair);
	
}



void SnakesAndLadders::setupGame()
{
	initBoard(Engine::board);

	currentPlayer = &players[0];
}

Status SnakesAndLadders::runGameLoop(){
    int playerId=currentPlayer->getId();
	playerId = abs(playerId);
	//cout<<
    int random = dice.getValue();
	int lastCoordinateBoard = coordinateBoard;
	board.setElement(arr[lastCoordinateBoard].getX(),arr[lastCoordinateBoard].getY() , 0 );
    coordinateBoard +=random;
	
	
    if(coordinateBoard>100){
        return Status::INVALID_MOVE;
    }
    for(int i = 0; i < 16 ;i++){
        if(snakes_ladders[i].getX()==coordinateBoard){
            coordinateBoard = snakes_ladders[i].getY();
            if(snakes_ladders[i].getX() > snakes_ladders[i].getY()){
			
                board.setElement(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() , playerId );
				cout<<"  x ->" <<arr[coordinateBoard].getX()<<arr[coordinateBoard].getY()<<endl; 
				return Status::BITE;
            }
            else{
				
				board.setElement(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() , playerId );
				cout<<"  x ->" <<arr[coordinateBoard].getX()<<arr[coordinateBoard].getY()<<endl; 
                return Status::CLIMB;
            }
        }
    }
	int cellState = isOpen(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() );
    cout<<"dAD"<<cellState<<endl;
    if(coordinateBoard == 100){
		cout<<"  x ->" <<arr[coordinateBoard].getX()<<arr[coordinateBoard].getY()<<endl; 
		board.setElement(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() , playerId );
				
        return Status::WIN;
    }
    else if(cellState != 0){
		cout<<"  x ->" <<arr[coordinateBoard].getX()<<arr[coordinateBoard].getY()<<endl; 
        board.setElement(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() , playerId );
        return Status::KILL;
    }
	cout<<"  x ->" <<arr[coordinateBoard].getX()<<arr[coordinateBoard].getY()<<endl; 
    board.setElement(arr[coordinateBoard].getX(),arr[coordinateBoard].getY() , playerId );
    return Status::SUCCESS;
    
    
}

void SnakesAndLadders::setBoard(int b[10][10] ){
	for(int i = 0; i < lengthBoard; i++){
		for(int j = 0 ; j< lengthBoard ;j++){
			board.setElement(i , j , b[i][j]);
		}
	}
}
/*Status SnakesAndLadders::runGameLoop()
{
	int random = dice.dice();
	int currentIndex = currentPlayer->getId() - 1;
	int xStep = coordinates[currentIndex].getX();
	int yStep = coordinates[currentIndex].getY();
	int xLast = xStep;
	int yLast = yStep;
	Status status= Status::SUCCESS;
	if (yStep == 9) {
		int temp1 = xStep - random;
		if (temp1 < 0) {
			
			//goto nextPlayer;
		}
		else {
			xStep = temp1;
		}
	}

	else if (yStep % 2 == 0) {
		int temp1 = random+ xStep;
		if (temp1 > 9) {
			int temp = 9 - xStep;
			xStep = 10;
			yStep++;
			xStep -= (random-temp);

		}
		else {
			xStep = temp1;
		}
	}

	else {
		int temp1 = xStep - random;
		if (temp1 < 0) {
			xStep = -1;
			yStep++;
			xStep -= temp1;

		}
		else {
			xStep = temp1;
		}
	}

	nextPlayer:

	int cellState = isOpen(xStep, yStep);
	if (cellState == currentPlayer->getId()) {
		status = Status::OUT_OF_BOUNDS;
	}
	else if (cellState == 0) {
		// empty cell
		coordinates[currentIndex].setX(xStep);
		coordinates[currentIndex].setY(yStep);
	}
	else if (cellState>0) {
		// killed frenzy !!!!
		coordinates[currentIndex].setX(xStep);
		coordinates[currentIndex].setY(yStep);
		int killedIndex = cellState - 1;
		coordinates[killedIndex].setX(-1);
		coordinates[killedIndex].setY(0);
		status = Status::KILL;
	}
	else if (cellState == -1) {
		// win !!!!
		coordinates[currentIndex].setX(xStep);
		coordinates[currentIndex].setY(yStep);
		status = Status::WIN;
		currentPlayer->setScore(1);

	}
	else if (cellState <= -3 && cellState >= -10) {
		// these ids are snakes
		int destination = snakes[-cellState - 3];
		yStep = destination / 10;
		if (destination % 10 == 0) {
			--yStep;
		}
		if (yStep % 2 == 0) {
			xStep = destination - yStep * 10;
			--xStep;
		}
		else {
			xStep = destination - yStep * 10;
			xStep = 10 - xStep;
		}
		coordinates[currentIndex].setX(xStep);
		coordinates[currentIndex].setY(yStep);
		status = Status::BITE;
	}
	else {
		// these ids are ladders
		int destination = ladders[-cellState - 3];
		yStep = destination / 10;
		if (destination % 10 == 0) {
			--yStep;
		}
		if (yStep % 2 == 0) {
			xStep = destination - yStep * 10;
			--xStep;
		}
		else {
			xStep = destination - yStep * 10;
			xStep = 10 - xStep;
		}
		coordinates[currentIndex].setX(xStep);
		coordinates[currentIndex].setY(yStep);
		status = Status::CLIMB;

	}
	if (random == 6) {
		if(status!=Status::WIN)
			status = Status::AWARD;
	}
	board.setElement(xLast, yLast, 0);
	board.setElement(xStep, yStep, currentPlayer->getId());
	return status;
}*/

void SnakesAndLadders::SaveScores()					// NOT COMPLETED //////////////////////////////////////////////////////
{
	// struct
	// end time of game
	// vector<Player> players
}

void SnakesAndLadders::teardownGame()
{
}

Status SnakesAndLadders::updateState(int x, int y)
{
	return Status();
}

bool SnakesAndLadders::canMove()
{
	return false;
}

void SnakesAndLadders::initBoard(Board &board)
{
	for (int i = 0; i < lengthBoard; i++)
	{
		for (int j = 0; j < lengthBoard; j++)
		{
			board.setElement(i, j, 0);
		}
	}
	
}

// void SnakesAndLadders::createDatabase(){
// 	char address[1000];
//     strcpy(address, "scoreGame");
//     DIR* dir = opendir(address);
//     if (dir)
//     {
//         /* Directory exists. */
//         //closedir(dir);
//     }
//     else if (ENOENT == errno)
//     {
//         mkdir(address, 0700);
//         system("touch ./scoreGame/scoreSnakesAndLadders.txt");

//     }
//     strcpy(address, "saveGame");
//     dir = opendir(address);
//     if (dir)
//     {
//         /* Directory exists. */
//         //closedir(dir);
//     }
//     else if (ENOENT == errno)
//     {
//         mkdir(address, 0700);
//         system("touch ./saveGame/saveSnakesAndLadders.txt");
//     }
    
// }

void SnakesAndLadders::fillCoordinates(){
    
    int len = 0;
    arr[len++]=Pair(0,0);
    for(int i = 0 ; i <= 9 ;i++){
        arr[len++]=Pair(9,i);
    }
    for(int i = 9 ; i >= 0 ;i--){
        arr[len++]=Pair(8,i);
    }
    for(int i = 0 ; i <= 9 ;i++){
        arr[len++]=Pair(7,i);
    }
    for(int i = 9 ; i >= 0 ;i--){
        arr[len++]=Pair(6,i);
    }
    for(int i = 0 ; i <= 9 ;i++){
        arr[len++]=Pair(5,i);
    }
    for(int i = 9 ; i >= 0 ;i--){
        arr[len++]=Pair(4,i);
    }
    for(int i = 0 ; i <= 9 ;i++){
        arr[len++]=Pair(3,i);
    }
    for(int i = 9 ; i >= 0 ;i--){
        arr[len++]=Pair(2,i);
    }
        for(int i = 0 ; i <= 9 ;i++){
        arr[len++]=Pair(1,i);
    }
        for(int i = 9 ; i >= 0 ;i--){
        arr[len++]=Pair(0,i);
    }
    
}
