#include <iostream>
using namespace std;

/***************************************************
 *Program PointerDemo
 *Programmer: Rob Garner (rgarner7@cnm.edu)
 *Date: 20140703
 *Purpose: Demonstrate pointers.
 ***************************************************/
void math(double num1, double num2, double* ptrSum, double* ptrSub);

int main()
{
	double number1 = 4;
	double number2 = 6;
	double summation = 0;
	double subtraction = 0;

	math(number1, number2, &summation, &subtraction);

	cout << endl << "Summation is: " << summation << endl;
	cout << endl << "Subtraction is: " << subtraction << endl;
	return 0;
}

void math(double num1, double num2, double* ptrSum, double* ptrSub)
{
	*ptrSum = num1 + num2;
	*ptrSub = num1 - num2;
}


