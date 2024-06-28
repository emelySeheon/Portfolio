/*
Program: Calender.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/3/20
Purpose: To provide functions for the driver.
*/

#include <iostream>;
#include <sstream>;
#include <fstream>
#include "Calender.h";
using namespace std;

//Course Header
string WriteHeader() {
	stringstream writeHeader;
	writeHeader << "Name: Emely Seheon" << endl
		<< "Program: SeheonP1" << endl
		<< "Objective: To display the month of the user's choice." << endl;
	string header = writeHeader.str();
	return header;
}

void AskForMonthAndYear(int& month, int& year)
{
	cout << "\nEnter the number of the month you would like to be displayed." << endl
	<< "(i.e. January is 1, February is 2, etc.) ";

	cin >> month;

	cout << "Enter the year that you would like to be displayed (minimum year is 1600). ";
	cin >> year;
}

int FillMonthGrid(int& month, int& year, int& weekDay)
{
	int days = 1;
	if (month == 1 || month == 3 || month == 5 ||
		month == 7 || month == 8 || month == 10 || month == 12) {
		days = 31;
	}
	else if (month == 4 || month == 6 || month == 9 || month == 11) {
		days = 30;
	}
	else if (month == 2) {
		if (year % 4 == 0) {
			days = 29;
		}
		else {
			days = 30;
		}
	}

	int currentMonth = month;
	int currentYear = year;
	//Algorithm from http://www.cplusplus.com/forum/general/174165/
	int a, y, m;
	a = (14 - currentMonth) / 12;
	y = currentYear - a;
	m = currentMonth + (12 * a) - 2;
	weekDay = (1 + y + (y / 4) - (y / 100) + (y / 400) + ((31 * m) / 12)) % 7;
	
	return days;
}

string CreateString(int daysInMonth, int& month, int& year, string monthsOfYear[], string daysOfWeek[], int& weekDay)
{
	stringstream displayMonthYear;
	stringstream displayWeekDays;
	stringstream displayDates;
	stringstream display;

	displayMonthYear << "                   " << monthsOfYear[month-1] << " " << year;
	string stringMonthYear = displayMonthYear.str();

	for (int i = 0; i < 7; i++) {
		displayWeekDays << "    " << daysOfWeek[i];
	}
	string stringweekDays = displayWeekDays.str();

	if (weekDay == 0) {
		displayDates << "     1      2      3      4      5      6      7" << endl
			<< "     8      9     10     11     12     13     14" << endl
			<< "    15     16     17     18     19     20     21" << endl
			<< "    22     23     24     25     26     27     28" << endl
			<< "    29"; 
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30     31";
		}
	}
	else if (weekDay == 1) {
		displayDates << "            1      2      3      4      5      6" << endl
			<< "     7      8      9     10     11     12     13" << endl
			<< "    14     15     16     17     18     19     20" << endl
			<< "    21     22     23     24     25     26     27" << endl
			<< "    28     29";
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30     31";
		}
	}
	else if (weekDay == 2) {
		displayDates << "                   1      2      3      4      5" << endl
			<< "     6      7      8      9     10     11     12" << endl
			<< "    13     14     15     16     17     18     19" << endl
			<< "    20     21     22     23     24     25     26" << endl
			<< "    27     28     29";
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30     31";
		}
	}
	else if (weekDay == 3) {
		displayDates << "                          1      2      3      4" << endl
			<< "     5      6      7      8      9     10      11" << endl
			<< "    12     13     14     15     16     17     18" << endl
			<< "    19     20     21     22     23     24     25" << endl
			<< "    26     27     28     29";
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30     31";
		}
	}
	else if (weekDay == 4) {
		displayDates << "                                 1      2      3" << endl
			<< "     4      5      6      7      8      9     10" << endl
			<< "    11     12     13     14     15     16     17" << endl
			<< "    18     19     20     21     22     23     24" << endl
			<< "    25     26     27     28     29";
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30     31";
		}
	}
	else if (weekDay == 5) {
		displayDates << "                                        1      2" << endl
			<< "     3      4      5      6      7      8      9" << endl
			<< "    10     11     12     13     14     15     16" << endl
			<< "    17     18     19     20     21     22     23" << endl
			<< "    24     25     26     27     28     29";
		if (daysInMonth == 30) {
			displayDates << "     30";
		}
		else if (daysInMonth == 31) {
			displayDates << "     30" << endl << "     31";
		}
	}
	else if (weekDay == 6) {
		displayDates << "                                               1" << endl
			<< "     2      3      4      5      6      7      8" << endl
			<< "     9     10     11     12     13     14     15" << endl
			<< "    16     17     18     19     20     21     22" << endl
			<< "    23     24     25     26     27     28     29";
		if (daysInMonth == 30) {
			displayDates << endl << "    30";
		}
		else if (daysInMonth == 31) {
			displayDates << endl << "    30     31";
		}
	}
	string stringDates = displayDates.str();

	display << endl << stringMonthYear << endl << stringweekDays << endl << stringDates << endl;
	string result = display.str();

	return result;
}

bool WriteMonth(string calender, int& month, int&year, string monthsOfYear[])
{
	bool noError;
	stringstream name;
	name << monthsOfYear[month - 1] << year << ".txt";
	string fileName = name.str();
	ofstream outputFile(fileName);
	outputFile << calender;
	if (outputFile.is_open()) {
		noError = true;
	}
	else {
		noError = false;
	}
	outputFile.close();
	return noError;
}

