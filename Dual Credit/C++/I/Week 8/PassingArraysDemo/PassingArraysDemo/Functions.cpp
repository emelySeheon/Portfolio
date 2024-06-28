/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: Functions for the driver
*/

#include <stdlib.h>;
using namespace std;

//Fills an array with random values between 1 and 100
void FillArray(int numbers[], int total) {

	for (int i = 0; i < total; ++i) {
		numbers[i] = rand() % 100 + 1;
	}

}

//Find sum of an array
int SumArray(int numbers[], int total)
{
	int sum = 0;
	for (int i = 0;i < total;++i) {
		sum += numbers[i];
	}

	return sum;
}

//Sort using the Bubble sort technique.
//Sorts array from low to high
void Sort(int numbers[], int total)
{

	int i, j, temp;

	//compare adjacent values, switch if out of order
	for (i = 0;i < total - 1;++i) {
		for (j = 1;j < total;++j) {
			if (numbers[j - 1] > numbers[j]) {
				temp = numbers[j];
				numbers[j] = numbers[j - 1];
				numbers[j - 1] = temp;
			}
		}
	}
}