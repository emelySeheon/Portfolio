/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/4/20
Purpose: To fix the broken code shown below.
*/



//Ivonne Nelson CIS 1275 C++ I
//Paint Calculator - Functions

//Functions.cpp  put function bodies here

#include <string>
#include <cmath>
#include <iostream>
#include "Functions.h"

using namespace std;

#define PI 3.14159;	

void WriteGreeting()
{
	cout << "\n\n Ivonne Nelson CIS 1275 C++ I - Paint Calculator."
		<< "\n This program will calculate the number of gallons "
		<< "of paint \nneeded to paint a room.";

}

//Put all parameters on one line for syntax to make it easier to read.
int HowManyGallons(double length, double width, double height, int coverage, int coats, string ceiling)
{
	double paintedArea = 0., exactPaint = 0.;
	paintedArea = 2.0 * height * (length + width);
	//Sytax, added curly brackets around the if
	if (ceiling == "y") {		//paint ceiling
		paintedArea += length * width;
	}

	paintedArea *= coats;	//how many coats

	exactPaint = paintedArea / coverage;
	int gallons = static_cast<int>(ceil(exactPaint));
	return gallons;

}

//Put all parameters on one line for syntax to make it easier to read. Also changed the 'g' in gallons from upper to lower case.
int HowManygallons(double radius, double height, int coverage, int coats, string ceiling)
{
	//creeated double pi so future pointer arrors won't appear, syntax
	double pi = 3.14159;
	double paintedArea = 0., exactPaint = 0.;
	//used pi instead of PI to avoid pointer errors, syntax
	paintedArea = pi * 2.0 * radius * height;
	//syntax error, added curly brackets aroud the if
	if (ceiling == "y") {	//paint ceiling
	//used pi instead of PI to avoid pointer errors, syntax
		paintedArea += pi * radius * radius;
	}

	paintedArea *= coats;	//how many coats

	exactPaint = paintedArea / coverage;
	int gallons = static_cast<int>(ceil(exactPaint));
	return gallons;
}