/*
Program: Emely Seheon
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/23/20
Purpose: Provide functions needed in StringStreamDemo
preject.
*/

#include "Functions.h"

string GetBankBalSummary(float balance) {
	stringstream bankBal;

	bankBal.precision(2);
	bankBal.setf(ios::fixed);
	bankBal << "Bank Balance: $" << balance << endl;
	string summary = bankBal.str();
	return summary;
}