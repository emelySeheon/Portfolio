#include "Measurement.h"
#include <cmath>
#include <iostream>

using namespace std;

Measurement Measurement::Simplify(int simpInches)
{
	int simpFeet{ 0 };
	if (simpInches >= 12)
	{
		simpFeet += (simpInches / 12);
		simpInches = simpInches % 12;
	}
	else if (simpInches < 0)
	{
		simpFeet -= ((abs(simpInches) / 12) + 1);
		simpInches = 12 - (abs(simpInches) % 12);
	}

	return Measurement(simpFeet, simpInches);
}

Measurement::Measurement()
{
}

Measurement::Measurement(int f, int i)
{
    feet = f ;
    inches = i ;
}

int Measurement::Multiply(Measurement obj)
{
	int inches1 = feet * 12 + inches;
	int inches2 = obj.feet * 12 + obj.inches;

	int simpInches = inches1 * inches2;
	return simpInches;
}

double Measurement::ConvertToDouble()
{
	double temp = feet;
	temp += (inches / 12.0);
	return temp;
}