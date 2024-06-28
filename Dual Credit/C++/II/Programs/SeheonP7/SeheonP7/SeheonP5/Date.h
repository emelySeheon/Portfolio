//File: Date.h

#ifndef _DATE_H
#define _DATE_H

#include <string>
using namespace std;


class Date
{
private:
	int month{ 1 }, day{ 1 }, year{ 1990 };
	string description;
	int dayOfYear;
	bool bLeap;
	void CalcDayOfYear();
	void DetermineLeapYear();

public:
	Date();
	Date(int m, int d, int y, string desc);
	void SetDate(int m, int d, int y, string desc);
	void SetDesc(string d) { description = d; }

	string GetFormattedDate();

	int GetDayOfYear() { return dayOfYear; }
	int GetYear() { return year; }
	int GetMonth() { return month; }
	int GetDay() { return day; }
	bool isLeapYear() { return bLeap; }
	bool ValidateThisDate();

	bool Date:: operator > (const Date d1);     //checks it Date d  is after (in time) Date d1
	bool Date:: operator < (const Date d1);     // checks it Date d  is before (in time) Date d1
	bool operator == (const Date d1);	// checks it Date d  is the same date as (in time) Date d1
	int Date:: operator - (const Date d1)  const;   // calculates the number of days between Date d and Date d1

};

#endif