/*
Program: VariableDeclarations
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 5/25/2020
Purpose: To learn how to declare variables
*/

#include <iostream>
using namespace std;

int main()
{
	//Demo that capitalization matters
	int total = 5;
	int TOTAL = 10;
	cout << "total: " << total << "\n";
	cout << "TOTAL: " << TOTAL << "\n";
	//cout << "Total: " << Total << "\n";

	//Demo that compiler doesn't care about carriage returns
	int myVariable = 15;
	cout << "myVariable: " << myVariable << "\n";

	//Demo declaring different data types
	char letterOfAlphabet = 'ab';
	cout << "letterOfAlphabet: " << letterOfAlphabet << "\n";
	int aWholeNumber = 25;
	cout << "aWholeNumber: " << aWholeNumber << "\n";
	float bankBalance = 500.00;
	cout << "bankBalance: " << bankBalance << "\n";
	double reallySmallNumber = 0.000000000000000000001;
	cout << "reallySmallNumber: " << reallySmallNumber << "\n";
	bool bProgramingIsFun = true;
	cout << "bProgramingIsFun: " << bProgramingIsFun << "\n";

	//Demo ASCII and chars as numbers
	char letterA = 65;
	cout << "letterA: " << letterA << "\n";
	// numbers from -128 to 127 correspond to a character

	//Demo mac values
	int largest = 2147483647;
	cout << "largest (should be 2147483647): " << largest << "\n";
	int tooLarge = 2147483648;
	cout << "tooLarge (should be 2147483648): " << tooLarge << "\n";
	unsigned int largestUnsignedInt = 4294967295;
	cout << "largestUnsignedInt (should be 4294967295): " << largestUnsignedInt << "\n";
	unsigned int tooLargeUnsignedInt = 4294967296;
	cout << "tooLargeUnsignedInt (should be 4294967296): " << tooLargeUnsignedInt << "\n";

	//Demo floating point Truncation
	float pi = 3.141592653589793;
	cout << "pi:" << pi << "\n";

	//Demo Ariane mishap
	float horizontalVelocity = 32768.0;
	cout << "horizontalVelocity (should be 32768.0):" << horizontalVelocity << "\n";
	short int intVelocity = horizontalVelocity;
	cout << "intVelocity (should be 32768.0): " << intVelocity << "\n";

	return 0;
}