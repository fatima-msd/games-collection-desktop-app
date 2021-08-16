#include "Reversi.h"


const int Reversi::sDirectionsTable[8][2] = {
		// { x, y }
		{ 0, 1 },	// up
		{ 1, 1 },	// up-left
		{ 1, 0 },	// left
		{ 1, -1 },	// down-left
		{ 0, -1 },	// down
		{ -1, -1 },	// down-right
		{ -1, 0 },	// right
		{ -1, 1 }	// up-right
	};

Reversi::Reversi()
{
	coordinate=new Pair(0,0);
}

Reversi::~Reversi()
{
}

void Reversi::fillPlayers(int num, string * arr)
{
	for (int i = 0; i < num; i++)
	{
		Player* p = new Player(i + 1, arr[i]);
		players.push_back(*p);
		//delete[] p;
		
	}
}

void Reversi::fillPlayersComputer(int id)
{

	
	Player* p = new Player(id, "AI");
	players.push_back(*p);
	

	
}

void Reversi::setupGame()
{
	initBoard(Engine::board);

	currentPlayer = &players[0];
}

void Reversi::setCurrentPlayer(int id){
	currentPlayer = &players[id];
}

Status Reversi::runGameLoop()
{
	Kill.erase(Kill.begin(),Kill.end());

	Status status= Status::SUCCESS;
	int currentId = currentPlayer->getId();
	currentId = abs( currentId);
	if(currentId == 3){
		currentId = 2;
	}
	int xStep=coordinate->getX();
	int yStep=coordinate->getY();
	int flag1=0;
	cout<<"size flip"<<PiecesToFlip.size()<<endl;
	for(int i=0;i<PiecesToFlip.size();i++){
		if(PiecesToFlip[i].getX()==xStep && PiecesToFlip[i].getY()==yStep){
			flag1=1;
			break;
		}
	}
	if(flag1==0){
		status=Status::INVALID_MOVE;
		return status;
	}
	else{
		int flag=0;
		
		for(int i=0;i<8;i++){
			xStep=coordinate->getX();
			yStep=coordinate->getY();
			int xDirection=sDirectionsTable[i][0];
			int yDirection=sDirectionsTable[i][1];
			cout<<"chek" <<isOnBoard(xStep+xDirection,yStep+yDirection)<<"  id" <<currentId<< "open"<<isOpen(xStep+xDirection,yStep+yDirection)<<endl;
			if(isOnBoard(xStep+xDirection,yStep+yDirection)){
				if(isOpen(xStep+xDirection,yStep+yDirection)!=currentId && isOpen(xStep+xDirection,yStep+yDirection)!=0){
					xStep+=xDirection;
					yStep+=yDirection;
					while(true){
						
						int checkBoard=isOnBoard(xStep,yStep);
						int checkopen=isOpen(xStep,yStep);
						cout<<"chek" <<checkopen<<"  id" <<currentId<<endl;
						if(checkBoard){
							if(checkopen!=currentId && checkopen!=0){
								xStep+=xDirection;
								yStep+=yDirection;

							}
							else if(checkopen==currentId){
								board.setElement(coordinate->getX(),coordinate->getY(),currentId);
								xStep-=xDirection;
								yStep-=yDirection;
								checkopen=isOpen(xStep,yStep);
								while(checkopen!=currentId){
									Pair pair1(xStep,yStep);
									Kill.push_back(pair1);
									
									board.setElement(xStep,yStep,currentId);
									
									xStep-=xDirection;
									yStep-=yDirection;
									checkopen=isOpen(xStep,yStep);
								}
								printBoard();
								break;

							}
							else{
								break;

							}
						}
						else{
							break;
						}

					}
				}
				else{
					continue;
				}
			}
			else{
				continue;
			}
		}
		
		// if(flag==1){
		// 	xStep=coordinate->getX();
		// 	yStep=coordinate->getY();
		// 	int xDirection=sDirectionsTable[index][0];
		// 	int yDirection=sDirectionsTable[index][1];
		// 	xStep+=xDirection;
		// 	yStep+=yDirection;
		// 	while(true){
		// 		int checkBoard=isOnBoard(xStep,yStep);
		// 		int checkopen=isOpen(xStep,yStep);
				
		// 		if(checkBoard){
		// 			if(checkopen!=currentId && checkopen!=0){
		// 				board.setElement(xStep,yStep,currentId);
		// 				xStep+=xDirection;
		// 				yStep+=yDirection;

		// 			}
		// 			else{
		// 				break;

		// 			}
		// 		}
		// 		else{
		// 			break;
		// 		}

		// 	}

			
		// 	status=Status::SUCCESS;

		// }

	}
	return status;
}

int Reversi::checkKilledPoints()
{

	
	int currentId = currentPlayer->getId();
	currentId = abs(currentId);
	if(currentId ==3 ){
		currentId = 2;
	}
	int xStep=coordinate->getX();
	int yStep=coordinate->getY();
	int out=0;
		for(int i=0;i<8;i++){
			xStep=coordinate->getX();
			yStep=coordinate->getY();
			int xDirection=sDirectionsTable[i][0];
			int yDirection=sDirectionsTable[i][1];
			if(isOnBoard(xStep+xDirection,yStep+yDirection)){
				if(isOpen(xStep+xDirection,yStep+yDirection)!=currentId && isOpen(xStep+xDirection,yStep+yDirection)!=0){
					xStep+=xDirection;
					yStep+=yDirection;
					while(true){
						
						int checkBoard=isOnBoard(xStep,yStep);
						int checkopen=isOpen(xStep,yStep);
						
						if(checkBoard){
							if(checkopen!=currentId && checkopen!=0){
								xStep+=xDirection;
								yStep+=yDirection;

							}
							else if(checkopen==currentId){
								//board.setElement(coordinate->getX(),coordinate->getY(),currentId);
								xStep-=xDirection;
								yStep-=yDirection;
								checkopen=isOpen(xStep,yStep);
								while(xStep!=coordinate->getX() || yStep!=coordinate->getY()){
									++out;
									xStep-=xDirection;
									yStep-=yDirection;
									checkopen=isOpen(xStep,yStep);
								}
								break;

							}
							else{
								break;

							}
						}
						else{
							break;
						}

					}
				}
				else{
					continue;
				}
			}
			else{
				continue;
			}
		}
		
	return out;
}

Status Reversi::aiEasyRunGameLoop(){
	int max=0;
	int index=-1;
	for(int k=0;k<PiecesToFlip.size();k++){
		coordinate->setX(PiecesToFlip[k].getX());
		coordinate->setY(PiecesToFlip[k].getY());
		int p=checkKilledPoints();
		if(p>max){
			max=p;
			index=k;
		}
		
	}
	coordinate->setX(PiecesToFlip[index].getX());
	coordinate->setY(PiecesToFlip[index].getY());
	return runGameLoop();


}


Status Reversi::aiHardRunGameLoop(){
	vector<Pair> Posible;
	vector<Pair> PiecesToFlipAi;
	//printBoard();
	for(int k=0;k<PiecesToFlip.size();k++){
		PiecesToFlipAi.push_back(PiecesToFlip[k]);
		//cout<<PiecesToFlip[k].getX()<<"  "<<PiecesToFlip[k].getY()<<endl;
	}
	int lastId=currentPlayer->getId();
	for(int k=0;k<PiecesToFlipAi.size();k++){
		coordinate->setX(PiecesToFlipAi[k].getX());
		coordinate->setY(PiecesToFlipAi[k].getY());
		Pair lastCoordinate(PiecesToFlipAi[k].getX(),PiecesToFlipAi[k].getY());

		int p=checkKilledPoints();
		//board.setElement(lastCoordinate.getX(),lastCoordinate.getY(),lastId);
		runGameLoop();
		// cout<<"run1"<<endl;
		// printBoard();
		togglePlayer();	
		flipPieces();
		int max=0;
		for(int j=0;j<PiecesToFlip.size();j++){
			coordinate->setX(PiecesToFlip[j].getX());
			coordinate->setY(PiecesToFlip[j].getY());
			int p1=checkKilledPoints();
			if(p1>max){
				max=p1;
				
			}
			
		}
		
		board.setElement(lastCoordinate.getX(),lastCoordinate.getY(),0);
		int playerId=idPlayer();
		for(int i=0;i<Kill.size();i++){
				board.setElement(Kill[i].getX(),Kill[i].getY(),1);
		
		}
		// cout<<"run3"<<endl;
		// printBoard();
		
		Pair pair(p,max);
		Posible.push_back(pair);
		PiecesToFlip.erase(PiecesToFlip.begin(),PiecesToFlip.end());
		for(int i=0;i<PiecesToFlipAi.size();i++){
			PiecesToFlip.push_back(PiecesToFlipAi[i]);
		}
		togglePlayer();	
		//flipPieces();
		
	}
	// cout<<"run4"<<endl;
	// 	printBoard();
	
	int index = 0;
	int max = Posible[0].getX() - Posible[0].getY();
	for(int i = 1 ; i < Posible.size() ; i++){
		int m = Posible[i].getX() - Posible[i].getY();
		//cout<<" x->"<<PiecesToFlip[i].getX()<<"  y->  "<<PiecesToFlip[i].getY()<<"Pc kill "<<Posible[i].getX() <<"1 kill  "<< Posible[i].getY()<<endl;
		if(m > max){
			max = m;
			index = i;
		}
		else if(m == max){
			if(Posible[i].getX() > Posible[index].getX()){
				max = m;
				index = i;

			}
		}

	}

	coordinate->setX(PiecesToFlip[index].getX());
	coordinate->setY(PiecesToFlip[index].getY());
	// cout<<"run1"<<endl;
	// 	printBoard();
	cout<<" x -> " <<coordinate->getX() << " y ---?"<<coordinate->getY()<<endl;
	return runGameLoop();


}


Status Reversi::flipPieces(){
	PiecesToFlip.erase(PiecesToFlip.begin(),PiecesToFlip.end());
	for(int i=0;i<lengthBoard;i++){
		for(int j=0;j<lengthBoard;j++){
			
			if(isValidMove(i,j)){
				Pair p(i,j);
                                //cout<<"-----------"<<endl<<i<<"  "<<j<<endl<<"______"<<endl;
				PiecesToFlip.push_back(p);
			}
		}
	}
	if(PiecesToFlip.size()==0){
		return Status::CANNOT_MOVE;
	}
	return Status::SUCCESS;
}


// vector<Pair> & Reversi::flipPiecesAi(){
// 	vector<Pair> *PiecesToFlipAi=new vector <Pair>;
	
// 	for(int i=0;i<lengthBoard;i++){
// 		for(int j=0;j<lengthBoard;j++){
// 			if(isValidMove(i,j)){
// 				Pair p(i,j);
// 				PiecesToFlipAi->push_back(p);
// 			}
// 		}
// 	}
// 	return *PiecesToFlipAi;
// }
bool Reversi::isValidMove(int x, int y) 
{

	int currentId = currentPlayer->getId();
	currentId = abs( currentId);
	if(currentId == 3){
		currentId = 2;
	}
	int xStep=x;
	int yStep=y;
	if(!isOnBoard(xStep,yStep) || isOpen(xStep,yStep)!=0){
		return false;	
	}
	//int flag=0;
	//int index=-1;
	for(int i=0;i<8;i++){
		xStep=x;
		yStep=y;
		int xDirection=sDirectionsTable[i][0];
		int yDirection=sDirectionsTable[i][1];
		if(isOnBoard(xStep+xDirection,yStep+yDirection)){
			if(isOpen(xStep+xDirection,yStep+yDirection)!=currentId && isOpen(xStep+xDirection,yStep+yDirection)!=0){
				xStep+=xDirection;
				yStep+=yDirection;
				while(true){
					
					int checkBoard=isOnBoard(xStep,yStep);
					int checkopen=isOpen(xStep,yStep);
					
					if(checkBoard){
						if(checkopen!=currentId && checkopen!=0){
							xStep+=xDirection;
							yStep+=yDirection;

						}
						else if(checkopen==currentId){
							return true;
							

						}
						else{
							break;

						}
					}
					else{
						break;
					}

				}
			}
			else{
				continue;
			}
		}
		else{
			continue;
		}
	}
	
	return false;
	

}

void Reversi::SaveScores()					// NOT COMPLETED //////////////////////////////////////////////////////
{
	// struct
	// end time of game
	// vector<Player> players
}

void Reversi::teardownGame()
{
}

Status Reversi::updateState(int x, int y)
{
	return Status();
}

bool Reversi::canMove()
{
	int i, j;
	for (j = 0; j < 8; j++) {
		for (i = 0; i < 8; i++) {
			if (isOpen(i, j) && isValidMove(i, j)) {
				return true;
			}
		}
	}

	return false;
}

void Reversi::initBoard(Board &board)
{
	for (int i = 0; i < lengthBoard; i++)
	{
		for (int j = 0; j < lengthBoard; j++)
		{
			board.setElement(i, j, 0);
		}
	}
	board.setElement(4, 5, players[1].getId());
	board.setElement(5, 4, players[1].getId());
	board.setElement(4, 4, players[0].getId());
	board.setElement(5, 5, players[0].getId());
	
}

void Reversi::setBoard(int b[10][10] ){
	for(int i = 0; i < lengthBoard; i++){
		for(int j = 0 ; j< lengthBoard ;j++){
			board.setElement(i , j , b[i][j]);
		}
	}
}

void Reversi::printBoard()
{
	cout<<"    ";
	for(int i=0;i<lengthBoard;i++){
		cout<<i<<"   ";
	}
	cout<<endl;
	int l=0;
	for(int i=0;i<lengthBoard;i++){
		for(int j=0;j<lengthBoard;j++){
			if(j==0){
				if(l==10)
					cout<<l++<<"  ";				
				else
					cout<<l++<<"   ";
			}
			int temp=board.getElement(i,j);
			switch(temp){
				case 0:
					cout<<".   ";
					break;

				case -2:
					cout<<"e   ";
					break;

				case -3:
					cout<<"h   ";
					break;

				default:
					cout<<temp<<"   ";
					break;
			}
			
		}
		cout<<endl;
	}
}

int Reversi::scores(){

	int currentId = currentPlayer->getId();
	currentId = abs(currentId);
	if(currentId == 3){
		currentId = 2;
	}
	int out=0;
	for(int i=0;i<lengthBoard;i++){
		for(int j=0;j<lengthBoard;j++){
			if(board.getElement(i,j)==currentId)
				++out;
		}
			
	}
	return out;
}

string Reversi::namePlayer(){
	return currentPlayer->getUserName();
}
int Reversi::idPlayer(){
	return currentPlayer->getId();
}

// void Reversi::createDatabase(){
// 	 char address[1000];
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
//         system("touch ./scoreGame/scoreReversi.txt");

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
//         system("touch ./saveGame/saveReversi.txt");
//     }
    
// }