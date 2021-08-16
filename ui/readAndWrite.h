#include <iostream>
#include <fstream>
#include "Status.h"
#include<string.h>
#include<vector>
#include "Pair.h"
using namespace std;






int readBoard(int arr[10][10]);
void itoa(int value , char & c);
int writeBoard(int arr[10][10]);
int readStatus(Status& status) ;
int writeStatus(Status status);
int writeNextStatus(Status status);
int readInfo(int& gameType, int& playerNum, string arr[]) ;
int writeFlip(vector<Pair> vec) ;
