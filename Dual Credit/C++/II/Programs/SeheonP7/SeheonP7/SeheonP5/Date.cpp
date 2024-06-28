//File: Date.cpp

#include <string>
#include <ctime>		//obtain system date
#include <sstream>
#include "Date.h"

using namespace std;

Date::Date()
{
	//Set the Date variables to the computer's date.
	time_t rawtime;
	tm OStime;

	time(&rawtime);

	//Old Way p.389
	//OStime = localtime(&rawtime);

	//New way, not deprecated
	localtime_s(&OStime, &rawtime);

	month = OStime.tm_mon + 1;
	day = OStime.tm_mday;
	year = OStime.tm_year + 1900;

	description = "Today's Date";
	DetermineLeapYear();
	CalcDayOfYear();
}


Date::Date(int m, int d, int y, string desc)
{
	month = m;
	day = d;
	year = y;
	description = desc;
	DetermineLeapYear();
	CalcDayOfYear();
}


void Date::SetDate(int m, int d, int y, string desc)
{
	month = m;
	day = d;
	year = y;
	description = desc;
	DetermineLeapYear();
	CalcDayOfYear();
}


string Date::GetFormattedDate()
{
	stringstream strDate;
	strDate << description;

	string monName[12] = { "January",	"February ","March",
		"April", "May", "June", "July", "August",
		"September", "October", "November", "December" };


	strDate << ": " << monName[month - 1] << " " << day
		<< ", " << year;

	return strDate.str();
}

bool Date::ValidateThisDate()
{
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
		if (day > 31) {
			return false;
		}
	}
	else if (month == 4 || month == 6 || month == 9 || month == 11) {
		if (day > 30) {
			return false;
		}
	}
	else {
		DetermineLeapYear();
		if (bLeap) {
			if (day > 29) {
				return false;
			}
		}
		else {
			if (day > 28) {
				return false;
			}
		}
	}
	return true;
}

void Date::CalcDayOfYear()
{
	//set up array of days in each month
	//for non-leapyear year
	int dayCount[12] = { 31,28,31,30,31,30,
		31,31,30,31,30,31 };

	dayOfYear = 0;

	//add the days up to the previous month
	for (int i = 1; i < month; ++i)
	{
		dayOfYear += dayCount[i - 1];

		//if adding Feb, check if leap year
		if (i == 2 && bLeap == true)
			dayOfYear += 1;
	}

	dayOfYear += day;
}

void Date::DetermineLeapYear()
{
	//A year is a leap year if it is divisible by four, 
	//unless it is a century date (i.e,  1900). 
	//If it is a century date, it is a leap year only 
	//if it is divisible by 400 (i.e., 2000).

	if (year % 4 == 0 && year % 100 != 0)
		bLeap = true;
	else if (year % 400 == 0)
		bLeap = true;
	else
		bLeap = false;
}

bool Date::operator>(const Date d1)
{
	if (year > d1.year
		|| (year == d1.year && month > d1.month)
		|| (year == d1.year && month == d1.month && day > d1.day)
		) {

		return true;
	}
	return false;
}

bool Date::operator<(const Date d1)
{
	if (year < d1.year
		|| (year == d1.year && month < d1.month)
		|| (year == d1.year && month == d1.month && day < d1.day)
		) {

		return true;
	}
	return false;
}

bool Date::operator==(const Date d1)
{
	return *this - d1 == 0;
}

int Date::operator-(const Date d1) const
{
	return dayOfYear - d1.dayOfYear;
}