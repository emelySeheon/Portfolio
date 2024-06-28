/*
Program: CalenderMonth.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 10/6/20
Purpose: To provide class functions for the driver.
*/

using namespace std;

#ifndef _CALENDERMONTH_H
#define _CALENDERMONTH_H

#include <vector>;

class CalendarMonth
{
private:
	int grid[6][7]{};
	int monthDays{ 30 };
	int month{ 1 };
	int year{ 2020 };
	int firstDay{ 1 };

	string formattedCalendar;
	string filename;
	bool bLeapYear{ false };
	bool bFileOK{ true };

	string dayNames[7] = { "Sun", "Mon", "Tue","Wed","Thu","Fri","Sat" };
	string monthName[12] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	int monthLength[12] = {};

	vector<string> vMonthComment;
	vector<string> vSpecialDateComment;
	vector<int> vSpecialDate;

	//private functions		
	void CheckLeapYear();
	void WriteMonth();


public:
	CalendarMonth();
	CalendarMonth(int m, int y);
	void SetCalMoInfo(int m, int y);
	void CreateMonthGrid();
	string GetFormattedString();
	bool IsFileOK();
	string GetFilename();
};


#endif