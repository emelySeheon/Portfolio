/*
Program: CarClass.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/10/20
Purpose: To provide functions for the driver
*/

#include <string>;
using namespace std;

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

class Car
{
private:    // Now we add our access modifier:
	string make{ "Mercedes" };
	string model{ "S450" };
	int year{ 2020 };
	string color{ "Black" };
public:  //Now add the public access modifier for our methods and constructor
	Car();  //default constructor
	Car(string make, string model, int year, string color);  //overloaded constructor
	~Car(); //destructor
	void SetCarInfo(string make, string model, int year, string color);
	string GetCarInfo();
};


#endif