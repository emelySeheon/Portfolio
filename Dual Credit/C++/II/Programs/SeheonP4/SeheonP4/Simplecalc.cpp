/*
Program: Simplecalc.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/22/20
Purpose: To provide functions for the driver
*/

#include <iostream>;
#include <sstream>;
#include <iomanip>
#include "Simplecalc.h";
using namespace std;

//Course Header
void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP2" << endl
		<< "Objective: To create a simple calculator." << endl;
}

void SimpleCalc::Calculate()
{
	switch (operation) {
	case '+':
		answer = operand1 + operand2;
		desc = "Your operation is addition: ";
		break;
	case '-':
		answer = operand1 - operand2;
		desc = "Your operation is subtraction: ";
		break;
	case '*':
		answer = operand1 * operand2;
		desc = "Your operation is multiplication: ";
		break;
	case '/':
		desc = "Your operation is division: ";
		if (operand2 != 0) {
			answer = operand1 / operand2;
		}
	}
}

SimpleCalc::SimpleCalc()
{
}

void SimpleCalc::SetOperation(char oper, double op1, double op2)
{
	operand1 = op1;
	operand2 = op2;
	operation = oper;
	Calculate();
}

string SimpleCalc::GetResults()
{
	stringstream outcome;
	outcome << setprecision(2) << fixed;

	if (operation == '/') {
		if (operand2 == 0) {
			outcome << "Illegal Operation!! :(";
		}
		else {
			outcome << answer;
		}
	}
	else {
		outcome << answer;
	}

	results = outcome.str();
	return results;
}
