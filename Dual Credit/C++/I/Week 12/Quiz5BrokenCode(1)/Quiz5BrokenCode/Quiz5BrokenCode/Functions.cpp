//Ivonne Nelson CIS 1275 C++ I
//Paint Calculator - Functions

//Functions.cpp  put function bodies here

#include <string>
#include <cmath>
#include <iostream>
#include "Functions.h"

using namespace std;

#define PI 3.14159;	

void WriteGreeting()
{
	cout<<"\n\n Ivonne Nelson CIS 1275 C++ I - Paint Calculator."
		<<"\n This program will calculate the number of gallons "
		<<"of paint \nneeded to paint a room."
		
}

int HowManyGallons(double length, double height, double width,
				   int coverage, int coats, string ceiling)
				  
{
	double paintedArea = 0., exactPaint = 0.;
	paintedArea = 2.0*height*(length+width);
	if(ceiling == "y")		//paint ceiling
		paintedArea +=length*width;

	paintedArea*= coats;	//how many coats

	exactPaint = paintedArea/coverage;
	int gallons = static_cast<int>(ceil(exactPaint));
	return gallons;

}
int HowManyGallons(double radius, double height,
				   int coverage, int coats, string ceiling)
{
	double paintedArea = 0., exactPaint = 0.;
	paintedArea = PI*2.0*radius*height;
	if(ceiling == "y")	//paint ceiling
		paintedArea += PI*radius*radius;

	paintedArea*= coats;	//how many coats

	exactPaint = paintedArea/coverage;
	int gallons = static_cast<int>(ceil(exactPaint));
	return gallons;
}