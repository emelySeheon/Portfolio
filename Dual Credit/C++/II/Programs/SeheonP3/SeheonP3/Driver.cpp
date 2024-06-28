/*
Program: SeheonP3
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 10/6/20
Purpose: To create the calender program with a class
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "CalenderMonth.h";
#include "Functions.h";
using namespace std;

int main()
{
	
	WriteHeader();

	string response = "no";
	int month = 0;
	int year = 0;
	bool fileOk;

	do {

		AskForMonthAndYear(month, year);
		if (month < 1 || month>12 || year < 1600) {
			cout << "\nError! The month and/or year you entered is/are invalid." << endl
				<< "Would you like to go again? (yes/no) ";
			cin >> response;
		}
		else {
			CalendarMonth userChoice(month, year);
			userChoice.SetCalMoInfo(month, year);
			userChoice.CreateMonthGrid();
			string isk  = userChoice.GetFormattedString();
			cout << isk;
			userChoice.GetFilename();
			fileOk = userChoice.IsFileOK();

			if (fileOk == true) {
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