/*
Program: RandomDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/11/20
Purpose: To demonstrate random number generation
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
using namespace std;

int main()
{
	
	//declare variables
	string doAnother;
	int diceRoll;

	//seed the random number generator using the timer
	srand((unsigned)time(NULL));

	//start a do while loop to allow user to run multiple times
	do
	{

		//display a random number
		diceRoll = rand() % 6 + 1;
		cout << diceRoll << endl;

		//ask user to do another?
		cout << "Do another (yes/no)? ";
		getline(cin, doAnother);

	//loop back if do another
	} while (doAnother == "yes");

	return 0;
}