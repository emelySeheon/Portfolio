/*
Program: ArrayDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: To demo arrays
*/

#include <iostream>;
#include <cmath>;
#include <string>;
using namespace std;

int main()
{
	
	string names[10] = { "Charles Babbage", "Alan Turing", "Tommy Flowers", "John Von Neuman", "Douglas Engelbart", "Steve Jobs", "Philip DOn Estridge", "Gordon Moore", "Bill Gates", "Tim Berners-Lee" };
	for (int i = 0; i < 5; ++i) {
		cout << "Name at " << i << "is " << names[i] << endl;
	}

	//Array out of bounds
	int high[4], low[4];
	for (int i = 0;i <= 4;++i) {
		low[i] = i + 5;
	}
	for (int i = 0;i <= 4;++i) {
		high[i] = i + 100;
	}

	return 0;
}