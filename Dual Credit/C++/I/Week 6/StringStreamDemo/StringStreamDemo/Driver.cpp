/*
Program: StringStreamDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/23/20
Purpose: To learn and understand stringstream
*/

#include "Functions.h"

int main()
{
	
	float balance = 10.0f;
	string result = GetBankBalSummary(balance);
	cout << result;

	return 0;
}