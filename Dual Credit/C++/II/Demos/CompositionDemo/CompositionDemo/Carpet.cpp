#include "Carpet.h"
#include <iostream>
#include <sstream>

using namespace std;

const double SQ_IN_TO_YARDS = 1296.0;

void Carpet::CalculateArea()
{
	area = (length.Multiply(width)) / SQ_IN_TO_YARDS;
}

Carpet::Carpet(int lengthFeet, int lengthInches, int widthFeet, int widthInches)
{
	length = Measurement(lengthFeet, lengthInches);
	width = Measurement(widthFeet, widthInches);
	CalculateArea();
}

string Carpet::GetCarpetStr()
{
	stringstream ss;
	ss << "\n The carpet is " << length.GetFeet() << " feet, "
		<< length.GetInches() << " inches" << " by " << width.GetFeet() << " feet, "
		<< width.GetInches() << " inches"
		<< "\n The area of the carpet is " << area << " square yards";
	return ss.str();
}
