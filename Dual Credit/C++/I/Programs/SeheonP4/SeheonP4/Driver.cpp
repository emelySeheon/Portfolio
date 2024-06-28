//Main Program

/*
Program: SeheonP4
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/1/20
Purpose: To determine if Mini Coopers can carry all that gold.
*/

#include <iostream>;
#include <iomanip>;
#include <vector>;
#include <string>;
#include "Functions.h"
using namespace std;

int main()
{

	//Course Header
	WriteHeader();

	//Declare/Initializing Variables
	string response;
	int cooperType;
	int numBars;
	int index;
	double barWeight;
	double cargoCapacity;

	//vectors for densities
	vector <double> densities;
	densities.push_back(0.098);
	densities.push_back(0.379);
	densities.push_back(0.619);
	densities.push_back(0.323);
	densities.push_back(0.773);


	//Do while loop
	do {
		//Getting User Input
		index = SelectMetalForBars();
		cooperType = SelectCooperModel();
		numBars = SelectNumberOfBars();

		//Calculations
		cargoCapacity = CalculateCargoCapacity(cooperType);
		barWeight = CalculateWeightOfBars(densities.at(index), numBars);

		//Displaying assumptions
		cout << "\nAssumptions made: Bars can be fit in the car with no extra volume unused." << endl
			<< "The metal is pure, so the density of the bars is the density of the metal." << endl;

		//Returning the answers
		double onrBarWt = barWeight / numBars;
		cout <<"\n" <<CarryBarWeight(barWeight);
		cout << FirBarVolume(numBars, cargoCapacity);
		cout << MaxCarBars(cargoCapacity, onrBarWt);

		//Asking to go again
		cout << "\nWould you like to go again? (yes/no) ";
		cin.ignore();
		getline(cin, response);

	} while (response == "yes");

	cout << "\nGoodBye!" << endl;

	return 0;
}
