/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 10/6/20
Purpose: To provide functions for the driver.
*/

#include <iostream>;
#include "Functions.h";
using namespace std;

//Course Header
void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP3" << endl
		<< "Objective: To create a calender using a class." << endl;
}

void AskForMonthAndYear(int& month, int& year)
{
	cout << "\nEnter the number of the month you would like to be displayed." << endl
		<< "(i.e. January is 1, February is 2, etc.) ";

	cin >> month;

	cout << "Enter the year that you would like to be displayed (minimum year is 1600). ";
	cin >> year;
}