/*
Program: PassingArraysDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: To demo passing arrays
*/

#include <iostream>
#include <stdlib.h>
#include <time.h>
#include "Functions.h"
using namespace std;

int main()
{
	srand(time(NULL));
	int arrayFromMain[15];
	cout << "This is the address of arrayFromMain: " << arrayFromMain << endl;
	
	//Sort the array
	Sort(arrayFromMain, 15);

	//Fill array with random values
	FillArray(arrayFromMain, 15);
	for (int i = 0;i < 15;++i) {
		cout << "Element at " << i << "is " << arrayFromMain[i] << endl;
	}

	//Show sum of elements in array
	int arraySum = SumArray(arrayFromMain, 15);
	cout << endl << "Sum of all elements of array: " << arraySum << endl;

	return 0;
}