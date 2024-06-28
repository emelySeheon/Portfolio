#ifndef _LOGGER_H
#define _LOGGER_H

#include <string>
#include <fstream>
#include"Deck.h"
#include"Hand.h"

using namespace std;

class Logger
{
private:
	string filename;	
	bool bLogOpen{false};
	string timeRightNow;  
	string dateTimeRightNow;  
	ofstream output;    
	void Time(); 
	void FileName();  	

public:
	Logger();
	void StartLog(double initialBal); 
	void WriteLog(string s);
	void CloseLog(string s);
	bool IsLogOpen() { return bLogOpen; }

};

#endif