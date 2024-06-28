/*
Program: ConditionalDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/2/20
Purpose: To practice conditionals.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
using namespace std;

int main()
{
	////Using if statements
	//string userInput;
	//string ynInput;

	//cout << "Enter A, B, or C: ";
	//getline(cin, userInput);
	//cout << "Enter y or n: ";
	//getline(cin, ynInput);

	//if (userInput == "A")
	//{
	//	cout << "You entered A!" << endl;
	//}
	//else if (userInput == "B")
	//{
	//	if (ynInput == "y")
	//	{
	//		cout << "You entered B and y!" << endl;
	//	}
	//	else if (ynInput == "n")
	//	{
	//		cout << "You entered B and n!" << endl;
	//	}
	//	else
	//	{
	//		cout << "You entered B but did not enter y or n! Error!" << endl;
	//	}
	//}
	//else if (userInput == "C")
	//{
	//	cout << "You entered C!" << endl;
	//}
	//else
	//{
	//	cout << "You did not enter A, B, or C! Error!" << endl;
	//}

	////Using Ternary Operator
	//float temprature;
	//string AirConditioner;

	////get the temprature from the user
	//cout << "Please enter the temprature: ";
	//cin >> temprature;

	////Assign value into temprature
	//AirConditioner = temprature > 80 ? "on" : "off";
	//cout << "\nThe air conditioner is " << AirConditioner << endl;

	//Switch statement
	char userInput;

	//get user input
	cout << "Please enter A, B, or C: ";
	cin >> userInput;

	//check user input
	switch (userInput)
	{
	case 'A':
		cout << "\nYou entered A!" << endl;
		break;
	case 'B':
		cout << "\nYou entered B!" << endl;
		break;
	case 'C':
		cout << "\nYou entered C!" << endl;
		break;
	default:
		cout << "\nYou did not enter A, B, or C! Error!" << endl;
		break;
	}

	return 0;
}