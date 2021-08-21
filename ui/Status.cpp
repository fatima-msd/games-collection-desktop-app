#include "Status.h"
int toStatus(char * c){
	string s(c);
	if(s=="SUCCESS"){
		return 0;
	}
	else if(s=="OUT_OF_BOUNDS"){
		return 1;
	}
	else if(s=="POSITION_FILLED"){
		return 2;
	}
	else if(s=="INVALID_MOVE"){
		return 3;
	}
	else if(s=="CANNOT_MOVE"){
		return  4;
	}
	else if(s=="QUIT"){
		return 5;
	}
	else if(s=="FINDING_MOVE"){
		return 6;
	}
	else if(s=="WIN"){
		return 7;
	}
	else if(s=="KILL"){
		return 8;
	}
	else if(s=="AWARD"){
		return 9;
	}
	else if(s=="BITE"){
		return 10;
	}
	else if(s=="CLIMB"){
		return 11;
	}
}

char* toString(Status s){
    char* out= new char[100];
	if(s==Status::SUCCESS){
        strcpy(out,"SUCCESS");
	}
	else if(s==Status::OUT_OF_BOUNDS ){
		strcpy(out,"OUT_OF_BOUNDS") ;
	}
	else if(s== Status::POSITION_FILLED){
		strcpy(out,"POSITION_FILLED");
	}
	else if(s== Status::INVALID_MOVE){
		strcpy(out,"INVALID_MOVE");
	}
	else if(s==Status::CANNOT_MOVE){
		strcpy(out,"CANNOT_MOVE");
	}
	else if(s==Status::QUIT){
		strcpy(out, "QUIT");
	}
	else if(s==Status::FINDING_MOVE){
		strcpy(out,"FINDING_MOVE");
	}
	else if(s==Status::WIN){
        strcpy(out,"WIN");
	}
	else if(s==Status::KILL){
        strcpy(out,"KILL");

	}
	else if(s==Status::AWARD){
		strcpy(out,"AWARD");
	}
	else if(s==Status::BITE){
		strcpy(out,"BITE");
	}
	else if(s==Status::CLIMB){
		strcpy(out,"CLIMB");
	}
    return out;
}