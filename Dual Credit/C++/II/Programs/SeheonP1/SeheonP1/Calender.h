/*
Program: Calender.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/3/200
Purpose: To provide functions for the driver.
*/

using namespace std;

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

string WriteHeader();
void AskForMonthAndYear(int &month, int& year);
int FillMonthGrid(int &month, int &year, int& weekDay);
string CreateString(int daysInMonth, int &month, int &year, string monthsOfYear[], string daysOfWeek[], int& weekDay);
bool WriteMonth(string calender, int& month, int& year, string monthsOfYear[]);

#endif