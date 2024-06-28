/*
Program: SeheonP1
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/3/20
Purpose: To create a calender.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "Calender.h";
using namespace std;

int main()
{
	string header;
	header = WriteHeader();
	cout << header;

	string response = "no";
	int month;
	int year;
	int weekDay;
	int daysInMonth;
	bool noError;
	string calender;
	string monthsOfYear[12] = { "January", "February", "March", "April", "May","June","July","August","September","October","November","December" };
	string daysOfWeek[7] = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	do {
		AskForMonthAndYear(month, year);
		if (month < 1 || month>12 || year < 1600) {
			cout << "\nError! The month and/or year you entered is/are invalid." << endl
				<< "Would you like to go again? (yes/no) ";
			cin >> response;
		}
		else {
			daysInMonth = FillMonthGrid(month, year, weekDay);
			calender = CreateString(daysInMonth, month, year, monthsOfYear, daysOfWeek, weekDay);
			cout << calender;
			noError = WriteMonth(calender, month, year, monthsOfYear);

			if (noError == true) {
				cout << "\nYour file was saved successfully." << endl;
			}
			else {
				cout << "\nYour file was not saved successfully." << endl;
			}

			cout << "\nWould you like to go again? (yes/no) ";
			cin >> response;
		}
	} while (response == "yes");
	cout << "\nGoodbye!" << endl;

	return 0;
}