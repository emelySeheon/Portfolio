/*
Program: LoopDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/2/20
Purpose: Demo loops
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
using namespace std;

int main()
{
	//While Loop
	/*int repeat;
	cout << "How many iterations (please enter a whole number)? ";
	cin >> repeat;

	while (repeat > 0)
	{
		cout << "This is iteration " << repeat-- << endl;
	}*/

	//Do while loop
	int repeat=0;
	string another;
	cout << "How many iterations (please enter a whole number)? ";
	cin >> repeat;

	do {
		cout << "This is iteration " << ++repeat << endl;
		cout << "Another iteration (yes/no)? ";
		getline(cin, another);
	} while (another == "yes");

	//For loop
	for (int i = 0;i < 10;++i)
	{
		cout << "Hello world number " << i << endl;
	}

	return 0;
}