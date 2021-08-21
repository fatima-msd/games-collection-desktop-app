#include "Reversi.h"
#include <fstream>

int main1(int n , string arr[],int playerType)//playerType=-2 -->computer Player easy////playerType=-3 -->computer Player hard
{	
	Reversi othello;
	//othello.createDatabase();
	othello.fillPlayers(n,arr);
	if(playerType==-2 || playerType==-3){
		othello.fillPlayersComputer(playerType);
	}	
	othello.setupGame();
	int player=othello.idPlayer();
	 Status s;
	 vector<Status>status;
	 othello.printBoard();
	 int x,y;
	while(true){
		s=othello.flipPieces();
		status.push_back(s);
		if(*(status.end()-1)==*(status.end()-2) && *(status.end()-1)==Status::CANNOT_MOVE){
			cout<<endl<<"End Game"<<endl;
			break;
		}
		if(s==Status::CANNOT_MOVE){
			cout<<"No valid move"<<endl;
			othello.togglePlayer();
			continue;
		}
		cout<<player<<" : "<<endl;
		if(player==-2){
			s=othello.aiEasyRunGameLoop();
		}
		else if(player==-3){
			s=othello.aiHardRunGameLoop();
		}
		else{
			cin>>x>>y;
			othello.coordinate->setX(x-1);
			othello.coordinate->setY(y-1);
			s=othello.runGameLoop();
		}
		if(s==Status::INVALID_MOVE){
			cout<<"Invalid Move .... Please Try Again"<<endl;
		}
		else{
			othello.printBoard();
			othello.togglePlayer();
			player=othello.idPlayer();
		}
	}
	int score1=othello.scores();
	string name1=othello.namePlayer();
	othello.togglePlayer();
	int score2=othello.scores();
	string name2=othello.namePlayer();
	cout<<"Player : "<<name1<<"Score : "<<score1<<endl;
	cout<<"Player : "<<name2<<"Score : "<<score2<<endl;
	if(score1>score2){
		cout<<name1<<" Win !!!"<<endl;
	}
	else if(score2>score1){
		cout<<name2<<" Win !!!"<<endl;

	}
	return 0;
}


int readBoard(int arr[10][10]) {
	int result = 1;				// OK
	fstream fp;
	fp.open("board.txt", ios::in);
	char num;
	int value;

	if (fp.is_open()) {
		for (int i = 0; i < 100; i++)
		{
			fp.read(&num, sizeof(char));
			value = atoi(&num);
			arr[i / 10][i % 10 ] = value;
			
			
		}
	}
	else {
		result = 0;
	}
	fp.close();
	return result;
}

void itoa(int value , char c){
	c = value + '0';
}

int writeBoard(int arr[10][10]) {				// c++ secondly write to board.bin
	int result = 1;				// OK
	fstream fp;
	fp.open("board.txt", ios::app);
	char num;
	int value;

	if (fp.is_open()) {
		for (int i = 0; i < 100; i++)
		{
			value = arr[i / 10][i % 10 ];
			
			itoa(value, num);				// 10 --> decimal
			fp.write(&num, sizeof(char));
		}
	}
	else {
		result = 0;
	}
	fp.close();
	return result;
}

int readStatus(Status& status) {
	int result = 1;
	fstream fp;
	
	fp.open("status.txt", ios::in);
	char s[100];

	if(fp.is_open()) {
		fp.read(s, 100 * sizeof(char));
		int numStatus =toStatus(s);
		switch(numStatus){
			case 0:
				status = Status::SUCCESS;
			break;
	
			case 1:
				status = Status::OUT_OF_BOUNDS;
			break;
			case 2:
				status = Status::POSITION_FILLED;
			break;
			case 3:
				status = Status::INVALID_MOVE;
			break;
			case 4:
				status = Status::CANNOT_MOVE;
			break;
			case 5:
				status = Status::QUIT;
			break;
			case 6:
				status = Status::FINDING_MOVE;
			break;
			case 7:
				status = Status::WIN;
			break;
			case 8:
				status = Status::KILL;
			break;
			case 9:
				status = Status::AWARD;
			break;
			case 10:
				status = Status::BITE;
			break;
			case 11:
				status = Status::CLIMB;
			break;

		}
		
		
	}
	else{
		result = 0;
	}
	fp.close();

	return result;
}

int writeStatus(Status status) {				// c++ first write to status.bin
	int result = 1;
	fstream fp;
	fp.open("status.txt", ios::out);
	char* s;
	if(fp.is_open()) {
		s = toString(status);
		fp.write(s, 100 * sizeof(char));
	}
	else{
		result = 0;
	}
	fp.close();
	return result;
}

int readInfo(int& gameType, int& playerNum, string arr[]) {
	int result = 1;						// OK
	char playerChar;
	fstream fp;
	fp.open("gameInfo.txt", ios::in);
	if(fp.is_open()) {
		fp.read(&playerChar, sizeof(char));
		gameType = - atoi(&playerChar);
		fp.read(&playerChar, sizeof(char));
		playerNum = atoi(&playerChar);

		for (int i = 0; i < playerNum; i++) {
			char c[100];
			fp.read(c, 100 * sizeof(char));
			string s(c);
			arr[i]=s;
		}
	}
	else{
		result = 0;
	}
	fp.close();
	return result;
}

int GameReversi(int x, int y , int turn) {
	int board[10][10];
	int result = readBoard(board);

	int gameType;
	int playerNum;

	string arr[10]; 
	result = readInfo(gameType, playerNum, arr);
	
	Status lastStatus ;
	Status javaStatus = Status::SUCCESS ;
	result = readStatus(lastStatus);

	Reversi othello;
	//othello.createDatabase();
	othello.fillPlayers(playerNum,arr);
	if(gameType==-2 || gameType==-3){
		othello.fillPlayersComputer(gameType);
	}	
	othello.setCurrentPlayer(turn);
	othello.setBoard(board);
	int player=othello.idPlayer();

	 Status s;
	 //vector<Status>status;
	 othello.printBoard();
	
		s=othello.flipPieces();
		if(s==lastStatus && s==Status::CANNOT_MOVE){
			cout<<endl<<"End Game"<<endl;
			javaStatus = Status::WIN;
			writeBoard(othello.board.board);
			writeStatus(javaStatus);
			return result;
		}
		if(s==Status::CANNOT_MOVE){
			cout<<"No valid move"<<endl;
			javaStatus = Status::CANNOT_MOVE;
			writeBoard(othello.board.board);
			writeStatus(javaStatus);
			return result;
			
		}
		cout<<player<<" : "<<endl;
		if(player==-2){
			s=othello.aiEasyRunGameLoop();
		}
		else if(player==-3){
			s=othello.aiHardRunGameLoop();
		}
		else{
			
			othello.coordinate->setX(x);
			othello.coordinate->setY(y);
			s=othello.runGameLoop();
		}
		if(s==Status::INVALID_MOVE){
			javaStatus = Status::INVALID_MOVE;
			writeBoard(othello.board.board);
			writeStatus(javaStatus);
			cout<<"Invalid Move .... Please Try Again"<<endl;
			return result;
		}
		else{
			 othello.printBoard();
			 javaStatus = Status::SUCCESS;
			 writeBoard(othello.board.board);
			 writeStatus(javaStatus);
			 return result;
			// othello.togglePlayer();
			// player=othello.idPlayer();
		}
	
	// int score1=othello.scores();
	// string name1=othello.namePlayer();
	// othello.togglePlayer();
	// int score2=othello.scores();
	// string name2=othello.namePlayer();
	// cout<<"Player : "<<name1<<"Score : "<<score1<<endl;
	// cout<<"Player : "<<name2<<"Score : "<<score2<<endl;
	// if(score1>score2){
	// 	cout<<name1<<" Win !!!"<<endl;
	// }
	// else if(score2>score1){
	// 	cout<<name2<<" Win !!!"<<endl;

	// }
	writeBoard(othello.board.board);
	writeStatus(javaStatus);
	return result;
}




int  main()
{
	fstream fp;
	fp.open("status.txt", ios::out);
	fp.close();
	GameReversi(3,5,0);
	return 0;
	
}