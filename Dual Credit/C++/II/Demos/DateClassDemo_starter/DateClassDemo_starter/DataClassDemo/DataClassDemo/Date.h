//File: Date.h

#ifndef _DATE_H
#define _DATE_H

#include <string>
using namespace std;



class Date
{
	private:
			int month, day, year;
			string description;
			int dayOfYear;
			bool bLeap;
			void CalcDayOfYear();
			void DetermineLeapYear();

	public:

			Date();
			Date(int m, int d, int y, string desc);

			void SetDate(int m, int d, int y, string desc);
			void SetDesc(string d){ description = d; }

			string GetFormattedDate();

			int GetDayOfYear()const { return dayOfYear;}
			int GetYear()const { return year;}
			int GetMonth()const { return month;}
			int GetDay()const { return day;}
			bool isLeapYear()const{ return bLeap; }
};



#endif