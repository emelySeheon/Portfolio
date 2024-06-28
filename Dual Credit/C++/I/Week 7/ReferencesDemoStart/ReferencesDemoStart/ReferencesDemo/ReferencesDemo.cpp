#include <iostream>
using namespace std;

/***************************************************
 *Program PointerDemo
 *Programmer: Emely Seheon (eseheon1@cnm.edu)
 *Date: 6/30/20
 *Purpose: Demonstrate references.
 ***************************************************/
void math(double num1, double num2, double& refSum, double& refSub);

int main()
{
	double number1 = 4;
	double number2 = 6;
	double summation = 0;
	double subtraction = 0;

	math(number1, number2, summation, subtraction);

	cout << endl << "Summation is: " << summation << endl;
	cout << endl << "Subtraction is: " << subtraction << endl;
	return 0;
}

void math(double num1, double num2, double &refSum, double& refSub)
{
	refSum = num1 + num2;
	refSub = num1 - num2;
}


