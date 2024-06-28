#ifndef CARPET_H
#define CARPET_H

#include <iostream>
#include "Measurement.h"

using namespace std;

class Carpet
{
private:
	Measurement width; 
	Measurement length;
	double area;
	void CalculateArea();
public:
	Carpet(int lengthFeet, int lengthInches, int widthFeet, int widthInches);
	double GetCarpetArea() { return area; };
	string GetCarpetStr();

};

#endif