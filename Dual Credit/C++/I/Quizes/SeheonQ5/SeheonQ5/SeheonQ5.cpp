/*
Program: SeheonQ5.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/4/20
Purpose: To fix the broken code shown below.
*/



//Ivonne Nelson CIS 1275 C++ I
//Paint Calculator - Functions

//Driver.cpp

#include <iostream>
#include "Functions.h"

int main()
{
	WriteGreeting();

	double length = 0., width = 0., height = 0., radius = 0.;
	int coverage = 200, coats = 1, shape = 1, gallons = 0;
	string ceiling = "n", name = "";


	cout << "\n\n Please enter your first and last names:\n\n";
	//Changed cin to getline because it is a string, not an int, logic fix
	getline (cin, name);

	cout << "\n\n Is your room rectangular(enter 1) or round(enter 2)?\n\n";
	cin >> shape;

	cout << "\n\n How many coats of paint do you want? ex. 2\n\n";
	cin >> coats;

	//Moved the specification of units to  before the '?' instead of at the end of the example in order to ensure users will not out units in their respponse, prevents logic error
	cout << "\n\n What is the coverage of your paint in sq ft/gal? ex. 250\n\n";
	cin >> coverage;

	cout << "\n\n Do you want to paint the ceiling? (y/n)\n\n";
	cin >> ceiling;

	//Added another equal sign, so that it is not an assignment
	if (shape == 1)	// if room is rectangular
	{
		cout << "\n\n Please enter the length, width  "
			<< "and height of your room in feet, \nleaving a space between "
			<< "the numbers:\n\n";
		cin >> length >> width >> height;

		gallons = HowManyGallons(length, width, height, coverage, coats, ceiling);

		cout << "\n\n " << name << ", ";
		cout << "Your room is " << length << " feet by " << width
			<< " feet and " << height << " feet high.";
	}
	if (shape == 2)	// if room is round
	{
		cout << "\n\n Please enter the radius and height of your room in feet,"
			<< "\n leaving a space between the numbers:\n\n";
		cin >> radius >> height;

		//Changed 'g' to lower to make sure the circle function is being used, syntax
		gallons = HowManygallons(radius, height, coverage, coats, ceiling);

		cout << "\n\n " << name << ", ";

		cout << " Your room is " << radius << " feet in diameter and "
			<< height << " feet high.";
	}


	if (ceiling == "y")
		cout << "\n\n You are painting your ceiling.";
	else
		cout << "\n\n You are not painting your ceiling.";

	cout << "\n\n You will need " << gallons
		<< " gallons of paint to paint your room " << coats << " coats.\n\n";


	return 0;
}