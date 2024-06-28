/*
Program: SeheonQ3
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/26/20
Purpose: To calculate the volume of a cone/
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "Functions.h"
using namespace std;

int main()
{
	
	WriteHeader();

	string response;

	do {
		double pi = 3.14159265;
		double radius;
		double height;

		cout << "\nWhat is the radius of the cone? (If it is a whole number, please put a \".0\" at the end) ";
		cin >> radius;
		cout << "What is the height of the cone? (If it is a whole number, please put a \".0\" at the end.)" << endl << "(Enter in the same unit as the radius.) ";
		cin >> height;

		double volume = CalcConeVolume(pi, radius, height);

		WriteConeVolume(radius, height, volume);

		cout << "\nWould you like to go again? (y/n) ";
		cin.ignore();
		getline(cin, response);
	} while (response == "y");

	cout << "\nGoodBye!" << endl;

	return 0;
}