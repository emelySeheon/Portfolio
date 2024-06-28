//Function Bodies

/*
Program: SeheonP4
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/1/20
Purpose: Provide functions.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
#include <sstream>;
#include "Functions.h";
using namespace std;

int cooperType;
int index;
int numBars;

//Course Header
void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP4" << endl
		<< "Objective: To determine if the Mini Coopers could carry all that stolen gold." << endl;
}

//Information on program goals
void WriteInstructions() {
	
}

//Select which cooper the user wants
int SelectCooperModel() {
	cout << "\nWould you like the 2 door or 4 door Mini Cooper? (Enter 2 or 4) ";
	cin >> cooperType;
	return cooperType;
}

//Select a metal
int SelectMetalForBars() {

	//Vectors for Metals
	vector <string> metals;
	metals.push_back("Aluminum");
	metals.push_back("Silver");
	metals.push_back("Gold");
	metals.push_back("Copper");
	metals.push_back("Platinum");

	string metalType;
	bool validInput;

	//Displaying planets to user
	for (int i = 0; i <= metals.size() - 1; ++i) {
		cout << "\n" << metals.at(i) << endl;
	}

	//Asking user for metal and returning the index
	cout << "\nSelect a metal: ";
	getline(cin, metalType);

	for (int j = 0; j <= metals.size(); ++j)
	{
		if (j <= metals.size() - 1) {
			if (metalType == metals.at(j)) {
				validInput = true;
				index = j;
				break;
			}
			else {
				continue;
			}
		}
	}
	return index;
}

//Asking for the number of bars
int SelectNumberOfBars() {
	cout << "\nHow many bars would you like? ";
	cin >> numBars;
	return numBars;
}

double CalculateWeightOfBars(double density, int numBars) {
	double weight = numBars * density * knoxVol * cubFeet;
	return weight;
}

//Returns the cargo capacity of the Mini Cooper in cubic inches
double CalculateCargoCapacity(int cooperType) {
	double cargoCapacity;

	if (cooperType == 2) {
		cargoCapacity = carVol2Door * cubFeet;
	}
	else if (cooperType == 4) {
		cargoCapacity = carVol4Door * cubFeet;
	}
	return cargoCapacity;
}

//Determines if the car can carry the weight
string CarryBarWeight(double barWeight) {
	stringstream weightInfo;
	string info;

	if (barWeight > maxWeight) {
		double differenceOver;

		differenceOver = barWeight - maxWeight;
		weightInfo << "The cargo is " << differenceOver << " pounds too heavy for the car." << endl;
		info = weightInfo.str();
	}
	else if (barWeight <= maxWeight) {
		double differenceUnder;

		differenceUnder = maxWeight - barWeight;
		weightInfo << "The car can support the cargo! The car can carry " << differenceUnder << " more pounds." << endl;
		info = weightInfo.str();
	}
	return info;
}

//Determines if the car can fit all the bars
string FirBarVolume(int numBars, double cargoCapacity) {
	stringstream volInfo;
	string canItFit;
	double barVolume = numBars * knoxVol*cubFeet;

	if (barVolume > cargoCapacity) {
		double differenceOver;
		double barsOver;

		differenceOver = barVolume - cargoCapacity;
		barsOver = differenceOver / knoxVol;

		volInfo << "The volume of the bars is " << differenceOver << " cubic inches over what the car can fit." << endl
			<< "You need to take out at least " << barsOver << " bars." << endl;
		canItFit = volInfo.str();
	}
	else if (barVolume <= cargoCapacity) {
		double differenceUnder;
		double barsUnder;

		differenceUnder = cargoCapacity - barVolume;
		barsUnder = knoxVol / differenceUnder;

		volInfo << "The bars fit! You can add at most " << barsUnder << " more bars." << endl;
		canItFit = volInfo.str();
	}
	return canItFit;
}

//Determines how many bars of the metal chosen can be carried according to weight and if they will fit
string MaxCarBars(double cargoCapacity, double onrBarWt) {
	stringstream maxBars;
	string max;
	double maxBarsWeight = maxWeight / onrBarWt;
	double maxBarsVol = cargoCapacity / (knoxVol * cubFeet);

	if (maxBarsWeight > maxBarsVol) {
		double differenceOver = maxBarsWeight - maxBarsVol;

		maxBars << "The amount of bars that the car can hold according to its weight is " << maxBarsWeight << "." << endl
			<< "However, " << maxBarsWeight << " bars is " << differenceOver << " bars over how many bars can fit in the car." << endl;
		max = maxBars.str();
	}
	else if (maxBarsWeight <= maxBarsVol) {
		maxBars << "The amount of bars that the car can hold according to its weight is " << maxBarsWeight << "." << endl
			<< "All of these bars will be able to fit in the car." << endl;
		max = maxBars.str();
	}
	return max;
}