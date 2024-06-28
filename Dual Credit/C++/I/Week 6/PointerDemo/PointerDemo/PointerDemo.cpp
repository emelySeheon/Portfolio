/*
Program: PointerDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/25/20
Purpose: Demonstrate Pointers
*/

#include <iostream>;
using namespace std;

void math(double num1, double num2, double* sum, double* sub);

int main()
{
	
	double number1 = 4;
	double number2 = 6;
	double summation = 0;
	double subtraction = 0;

	math(number1, number2, &summation, &subtraction);

	//display results of our function?
	cout << "Summation is: " << summation << endl;
	cout << "Subtraction is: " << subtraction << endl;

	return 0;
}

void math(double num1, double num2, double* sum, double* sub) {
	*sum = num1 + num2;
	*sub = num1 - num2;
}