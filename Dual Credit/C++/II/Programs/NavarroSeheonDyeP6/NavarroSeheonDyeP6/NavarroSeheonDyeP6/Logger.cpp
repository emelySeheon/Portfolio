#include "Logger.h"
#include <ctime>
#include <chrono>
#include<sstream>
#include <iomanip>


void Logger::Time()
{	
	auto now = std::chrono::system_clock::now();
	auto in_time_t = std::chrono::system_clock::to_time_t(now);

	stringstream ss;
	ss << std::put_time(std::localtime(&in_time_t), "%X");
	timeRightNow = ss.str();
	
}

void Logger::FileName()
{
	auto now = std::chrono::system_clock::now();
	auto in_time_t = std::chrono::system_clock::to_time_t(now);

	stringstream ss;
	ss << std::put_time(std::localtime(&in_time_t), "%m-%d-%Y_%I.%M.%S");
	dateTimeRightNow = ss.str();
	ss << "-blackjack.txt";
	filename = ss.str();

}

Logger::Logger()
{
	FileName();
	Time();
	output.open(filename);
	if (output.is_open())
	{			
		output << "Welcome to the C++ BlackJack Card Game! Game started at:  "
			<< timeRightNow << "\r\n";
		bLogOpen = true;
	}
	else
	{
		bLogOpen = false;
	}
}

void Logger::StartLog(double initialBal)
{
	//add the user initial balance
	output << "Your starting balance is: $" << initialBal << "\r\n";
}

void Logger::WriteLog(string s)
{	
	output << s; 
}

void Logger::CloseLog(string s)
{
	//write the string
	output << "\r\n" << s;
	output.close();
	string openfile = "notepad.exe" + filename;
	system(openfile.c_str());
}


