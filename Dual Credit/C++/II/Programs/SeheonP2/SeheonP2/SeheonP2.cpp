/*
Program: SeheonP2
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/22/20
Purpose: To create a simple calculator.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "Simplecalc.h";
using namespace std;

int main()
{
	WriteHeader();
	string goAgain = "yes";
	SimpleCalc equation;
	string opr;
	string result;
	char oper;
	double op1;
	double op2;

	while (goAgain == "yes") {
		cout << "\nWelcome! What is the operation? ";
		getline(cin, opr);

		for (int i = 0; i < opr.size(); ++i) {
			oper = opr[i];
		}

		cout << "\nWhat is your first number? ";
		cin >> op1;

		cout << "\nWhat is your second number? ";
		cin >> op2;

		equation.SetOperation(oper, op1, op2);
		result = equation.GetResults();

		cout << result << "\nWould you like to go again (yes/no)? ";
		cin.ignore();
		getline(cin, goAgain);
	}

	return 0;
}