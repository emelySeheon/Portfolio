/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/26/20
Purpose: To provide functions for the driver
*/

#include "Functions.h"
#include <iostream>;
#include <sstream>;
using namespace std;

void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonQ3" << endl
		<< "Objective: To calculate the volume of a cone." << endl;
}

double CalcConeVolume(double pi, double radius, double height)
{
	double volume = (pi * radius * radius * height) / 3;
	return volume;
}

void WriteConeVolume(double radius, double height, double volume)
{
	stringstream results;

	results << "\nThe radius is: " << radius << "." << endl
		<< "The height is: " << height << "." << endl
		<< "The Volume is: ";

	results.precision(3);
	results << volume << ".";

	string result = results.str();
	cout << result << endl;
}