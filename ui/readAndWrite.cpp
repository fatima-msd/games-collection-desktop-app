
#include "readAndWrite.h"
// Implementation of the native method sayHello()
using namespace std;

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

void itoa(int value , char & c){
	c = value + '0';
}

int writeBoard(int arr[10][10]) {				// c++ secondly write to board.bin
	int result = 1;				// OK
	fstream fp;
	fp.open("board.txt", ios::out);
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
		int len = strlen(s);
		char s1='\0';
		for(int i=0;i<100;i++){
		if(i<len)
			fp.write(&s[i],sizeof(char));
		else
			fp.write(&s1,sizeof(char));

		}
	}
	else{
		result = 0;
	}
	fp.close();
	return result;
}
int writeNextStatus(Status status) {				// c++ first write to status.bin
	int result = 1;
	fstream fp;
	fp.open("nextStatus.txt", ios::out);
	char* s;
	if(fp.is_open()) {
		s = toString(status);
		int len = strlen(s);
		char s1='\0';
		for(int i=0;i<100;i++){
		if(i<len)
			fp.write(&s[i],sizeof(char));
		else
			fp.write(&s1,sizeof(char));

		}
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
		gameType = -1 * atoi(&playerChar);
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
   // cout<<gameType << "  " << playerNum << "   " << arr[0]<< arr[1]<<endl;
	return result;
}

int writeFlip(vector<Pair> vec) {				// c++ secondly write to board.bin
	int result = 1;				// OK
	fstream fp;
	fp.open("flip.txt", ios::out);
	char num;
	int value;

	if (fp.is_open()) {
		int len = vec.size();
		while(len){
                    
			int n = len % 10;
			len /= 10;
			itoa(n, num);
			fp.write(&num, sizeof(char));
			
                       // cout<<num;
		}
		len = vec.size();
		for (int i = 0; i < len; i++)
		{
			value = vec[i].getX();
			
			itoa(value, num);				// 10 --> decimal
			fp.write(&num, sizeof(char));
            value = vec[i].getY();
			itoa(value, num);
            fp.write(&num, sizeof(char));
		}
	}
	else {
		result = 0;
	}
	fp.close();
	return result;
}
