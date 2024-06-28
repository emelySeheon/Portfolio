/*
Program: SeheonP7pt2
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/7/20
Purpose: To calculate rain data for Maryland in April 1989
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <sstream>;
#include "Functions.h";
using namespace std;

int main()
{
	string results;
	int days[30];
	double data[30];

	read(days, data);
	results = analyze(days, data);
	write(results);

	return 0;
}