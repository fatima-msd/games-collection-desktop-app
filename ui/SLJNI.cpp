#include <jni.h>       // JNI header provided by JDK

#include "SLJNI.h"  // Generated
#include "SnakesAndLadders.h"
#include "Status.h"
#include "readAndWrite.h"
#include <fstream>

// Implementation of the native method sayHello()





JNIEXPORT jint JNICALL Java_ui_SnakeAndLaddersBoard_DiceSnakesAndLadders(JNIEnv *env, jobject thisObj) {

    SnakesAndLadders snakesAndLadders;
    return snakesAndLadders.dice.dice();
}

JNIEXPORT jint JNICALL Java_ui_SnakeAndLaddersBoard_GameSnakesAndLadders(JNIEnv *env, jobject thisObj,jint x , jint d , jint turn) {
	int board[10][10];
	int result = readBoard(board);

	int gameType;
	int playerNum;

	string arr[10]; 
	result = readInfo(gameType, playerNum, arr);
	
	
	Status javaStatus = Status::SUCCESS ;
	

	SnakesAndLadders snakesAndLadders;
	
	snakesAndLadders.fillPlayers(playerNum,arr);
        
	// if(gameType==-2 ){
    //             cout<<"AI"<<endl;
	// 	snakesAndLadders.fillPlayersComputer();
	// }	
	snakesAndLadders.setCurrentPlayer(turn);
    //snakesAndLadders.initBoard(board);
	snakesAndLadders.setBoard(board);
    snakesAndLadders.dice.setValue(d);
    snakesAndLadders.coordinateBoard = x;
	int player=snakesAndLadders.idPlayer();
	Status s = snakesAndLadders.runGameLoop();
	cout<<s<<" : "<<endl;
	
	
		
	
	if(s==Status::INVALID_MOVE){
            javaStatus = Status::INVALID_MOVE;
            writeBoard(snakesAndLadders.board.board);
            writeStatus(javaStatus);
            cout<<"Invalid Move .... Please Try Again"<<endl;
            return result;
	}
	else{
			
           // javaStatus = Status::SUCCESS;
            writeBoard(snakesAndLadders.board.board);
            writeStatus(s);
            return result;
		
	}
	
	
	writeBoard(snakesAndLadders.board.board);
	writeStatus(s);
	return result;
}


// int  main()
// {
// 	fstream fp;
// 	fp.open("status.txt", ios::out);
// 	fp.close();
// 	GameReversi(3,5,0);
// 	return 0;
	
// }