/*
Program: Functions.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/36/30
Purpose: To provide functions for the driver.
*/

using namespace std;

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

void WriteHeader();
void ShowRules();
bool Read(string someArray[], string filename);
int SwapColors(int numSwapped[], string sentences[], string colors[], string phrases[]);
bool WriteResults(string sentences[], int totalSentModified, int numSwapped[], ofstream& output, string& results);
bool WriteOriginals(string sentences[], string colors[], string phrases[], ofstream& output);
void SayGoodbye();

#endif