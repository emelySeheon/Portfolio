/*
Program:CalenderMonth.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 10/6/20
Purpose: To provide class functions for the driver.
*/

#include <iostream>;
#include <sstream>
#include <fstream>
#include "CalenderMonth.h";
using namespace std;

//Constructors
CalendarMonth::CalendarMonth()
{
}

CalendarMonth::CalendarMonth(int m, int y)
{
		month = m;
		year = y;
	}
}

//Private
void CalendarMonth::CheckLeapYear()
{
	if (month == 1 || month == 3 || month == 5 ||
		month == 7 || month == 8 || month == 10 || month == 12) {
		monthDays = 31;
	}
	else if (month == 4 || month == 6 || month == 9 || month == 11) {
		monthDays = 30;
	}
	else if (month == 2) {
		if (year % 4 == 0) {
			monthDays = 29;
			bLeapYear == true;
		}
		else {
			monthDays = 30;
		}
	}
}

void CalendarMonth::WriteMonth()
{
	ofstream outputFile(filename);
	outputFile << formattedCalendar;
	if (outputFile.is_open()) {
		bFileOK = true;
	}
	else {
		bFileOK = false;
	}
	outputFile.close();
}


//Public

//I wasn't sure what this method was supposed to do, sorry if I did it wrong
void CalendarMonth::SetCalMoInfo(int m, int y)
{
	vMonthComment.push_back("January is Slavery and Human Trafficking Prevention Month");
	vMonthComment.push_back("February is Black History Month");
	vMonthComment.push_back("March is Woman's History Month");
	vMonthComment.push_back("April is National Child Abuse Prevention Month");
	vMonthComment.push_back("May is Mental Health Awareness Month");
	vMonthComment.push_back("June is LGBTQI+ Pride Month");
	vMonthComment.push_back("July is National Ice Cream Month");
	vMonthComment.push_back("August is Cataract Awareness Month");
	vMonthComment.push_back("September is Pain Awareness Month");
	vMonthComment.push_back("October is Breast Cancer Awareness Month");
	vMonthComment.push_back("November is National Alzheimer's Disease Awareness Month");
	vMonthComment.push_back("December is Universal Human Rights Month");

	vSpecialDateComment.push_back("* January 15th is Strawberry Ice Cream Day *");
	vSpecialDateComment.push_back("* February 15th is Gumdrop Day *");
	vSpecialDateComment.push_back("* March 15th is Everything You Think is Wrong Day *");
	vSpecialDateComment.push_back("* April 15th is McDonald's Day *");
	vSpecialDateComment.push_back("* May 15th is Chocolate Chip Day *");
	vSpecialDateComment.push_back("* June 15th is Nature Photography Day *");
	vSpecialDateComment.push_back("* July 15th is Gummy Worm Day *");
	vSpecialDateComment.push_back("* August 15th is Relaxation Day *");
	vSpecialDateComment.push_back("* September 15th is Make a Hat Day *");
	vSpecialDateComment.push_back("* October 15th is I Love Lucy Day *");
	vSpecialDateComment.push_back("* November 15th is Clean Out Your Refrigerator Day *");
	vSpecialDateComment.push_back("* December 15th is International Tea Day *");
}

void CalendarMonth::CreateMonthGrid()
{
	CheckLeapYear();

	int currentMonth = month;
	int currentYear = year;
	//Algorithm from http://www.cplusplus.com/forum/general/174165/
	int a, y, m;
	a = (14 - currentMonth) / 12;
	y = currentYear - a;
	m = currentMonth + (12 * a) - 2;
	firstDay = (1 + y + (y / 4) - (y / 100) + (y / 400) + ((31 * m) / 12)) % 7;
}

string CalendarMonth::GetFormattedString()
{
	stringstream displayMonthYear;
	stringstream displayWeekDays;
	stringstream displayDates;
	stringstream display;
	stringstream specialEvents;

	displayMonthYear << "                   " << monthName[month - 1] << " " << year;
	string stringMonthYear = displayMonthYear.str();

	for (int i = 0; i < 7; i++) {
		displayWeekDays << "    " << dayNames[i];
	}
	string stringweekDays = displayWeekDays.str();

	if (firstDay == 0) {
		displayDates << "     1      2      3      4      5      6      7" << endl
			<< "     8      9     10     11     12     13     14" << endl
			<< "   *15*    16     17     18     19     20     21" << endl
			<< "    22     23     24     25     26     27     28" << endl
			<< "    29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30     31";
		}
	}
	else if (firstDay == 1) {
		displayDates << "            1      2      3      4      5      6" << endl
			<< "     7      8      9     10     11     12     13" << endl
			<< "    14    *15*    16     17     18     19     20" << endl
			<< "    21     22     23     24     25     26     27" << endl
			<< "    28     29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30     31";
		}
	}
	else if (firstDay == 2) {
		displayDates << "                   1      2      3      4      5" << endl
			<< "     6      7      8      9     10     11     12" << endl
			<< "    13     14    *15*    16     17     18     19" << endl
			<< "    20     21     22     23     24     25     26" << endl
			<< "    27     28     29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30     31";
		}
	}
	else if (firstDay == 3) {
		displayDates << "                          1      2      3      4" << endl
			<< "     5      6      7      8      9     10      11" << endl
			<< "    12     13     14    *15*    16     17     18" << endl
			<< "    19     20     21     22     23     24     25" << endl
			<< "    26     27     28     29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30     31";
		}
	}
	else if (firstDay == 4) {
		displayDates << "                                 1      2      3" << endl
			<< "     4      5      6      7      8      9     10" << endl
			<< "    11     12     13     14    *15*    16     17" << endl
			<< "    18     19     20     21     22     23     24" << endl
			<< "    25     26     27     28     29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30     31";
		}
	}
	else if (firstDay == 5) {
		displayDates << "                                        1      2" << endl
			<< "     3      4      5      6      7      8      9" << endl
			<< "    10     11     12     13     14    *15*    16" << endl
			<< "    17     18     19     20     21     22     23" << endl
			<< "    24     25     26     27     28     29";
		if (monthDays == 30) {
			displayDates << "     30";
		}
		else if (monthDays == 31) {
			displayDates << "     30" << endl << "     31";
		}
	}
	else if (firstDay == 6) {
		displayDates << "                                               1" << endl
			<< "     2      3      4      5      6      7      8" << endl
			<< "     9     10     11     12     13     14    *15*" << endl
			<< "    16     17     18     19     20     21     22" << endl
			<< "    23     24     25     26     27     28     29";
		if (monthDays == 30) {
			displayDates << endl << "    30";
		}
		else if (monthDays == 31) {
			displayDates << endl << "    30     31";
		}
	}
	string stringDates = displayDates.str();

	for (int i = 1; i <= 12; ++i) {
		if (i == month){
			specialEvents << vSpecialDateComment.at(i - 1) << endl << vMonthComment.at(i - 1);
		}
	}

	string events = specialEvents.str();

	display << endl << stringMonthYear << endl << stringweekDays << endl << stringDates << endl << events << endl;
	string result = display.str();

	formattedCalendar = result;
	return result;
}

bool CalendarMonth::IsFileOK()
{
	WriteMonth();
	bool noError;
	if (bFileOK == true) {
		noError = true;
	}
	else {
		noError = false;
	}
	return noError;
}

string CalendarMonth::GetFilename()
{
	stringstream name;
	name << monthName[month - 1] << year << ".txt";
	filename = name.str();
	return filename;
}
