/*
Program: Simplecalc.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/22/20
Purpose: To provide functions for the driver
*/

#ifndef SIMPLECALC_H
#define _SIMPLECALC_H
#include <string>
using namespace std;

void WriteHeader();

class SimpleCalc
{
private:
	char operation = '+';	//which arithmetic operation is chosen
	double operand1 = 0.0;
	double operand2 = 0.0;
	double answer = 0.0;	//the answer in numerical form
	string results = "";	//formatted string
	string desc = "";	//description of the operation (optional)	
	void Calculate();
public:
	SimpleCalc();	//default constructor		
	void SetOperation(char oper, double op1, double op2);
	string GetResults();

};
#endif