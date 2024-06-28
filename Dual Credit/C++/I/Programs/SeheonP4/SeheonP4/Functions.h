//Function Prototypes

/*
Program: SeheonP4
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/1/20
Purpose: Provide function headers.
*/

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

#include <iostream>;
#include <string>;
using namespace std;

//Function Prototypes
void WriteHeader();
void WriteInstructions();
int SelectCooperModel();
int SelectMetalForBars();
int SelectNumberOfBars();
double CalculateWeightOfBars(double density, int numBars);
double CalculateCargoCapacity(int cooperType);
string CarryBarWeight(double barWeight);
string FirBarVolume(int numBars, double cargoCapacity);
string MaxCarBars(double cargoCapacity, double onrBarWt);

//Program Constants
const double carVol2Door = 24.0; //cubic feet, cargo volume of a 2 door mini
const double carVol4Door = 32.8; //cubic feet, cargo volume of a 4 door mini
const double knoxVol = 0.02569806; //cubic feet, volume of a Fort Knox gold bar
const double maxWeight = 1800.0; //pounds, max weight that can be caried in a mini
const double cubFeet = 1728.0; //Divide cubic inches by this number to get cubic feet

#endif
