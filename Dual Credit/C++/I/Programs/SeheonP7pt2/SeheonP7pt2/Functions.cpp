/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/7/20
Purpose: To provide functions for the driver
*/

#include <iostream>;
#include <fstream>;
#include <iomanip>;
#include <string>;
#include <sstream>;
#include "Functions.h";
using namespace std;

//Read the file
void read(int days[], double data[])
{
	string lines[61] = {};
	int i = 0;
	int h = 0;

	//Reading the file
	string text;
	string fileName = "input.txt";
	ifstream inputFile;
	inputFile.open(fileName);
	while (getline(inputFile, text)) {
		if (h == 0 || h == 31) {
			++h;
			continue;
			}
		lines[i] = text;
		++i;
		++h;
		}

	//Putting file info in arrays
	for (int j = 0; j < 61; ++j) {
		stringstream convert(lines[j]);
		if (j < 30) {
			convert >> days[j];
		}
		else {
			convert >> data[j - 30];
		}
	}
}

//Analyze the data
string analyze(int days[], double data[])
{
	stringstream results; 
	double percent = percentMonth(data);
	results << "It rained during " << percent << "% of the month in April 1989 in Maryland" << endl;

	double average = averageRain(data);
	results << "The average rainfall for April 1989 in Maryland is " << average << " inches." << endl;

	int date = dayMost(days, data);
	results << "It rained the most on April " << date << " 1989 in Maryland out of the month." << endl;

	string output = results.str();

	return output;
}

//Calculate  the percent of the month it was raining
double percentMonth(double data[])
{
	int i = 0;
	for (int j = 0; j < 30; ++j) {
		if (data[j] != 0) {
			i++;
		}
	}
	double percent = i * 100;
	percent = percent / 30;

	return percent;
}

//calculate the average rain for the month
double averageRain(double data[])
{
	double sum = 0;
	for (int i = 0; i < 30; ++i) {
		sum = sum + data[i];
	}
	double average = sum / 30;
	return average;
}

//find the day it was raining the most
int dayMost(int days[], double data[])
{
	int index = 0;
	for (int i = 1; i < 30; ++i) {
		if (data[i] > data[index]) {
			index = i;
		}
	}

	int date = days[index];

	return date;
}

//Write into file
void write(string results)
{
	string fileName;
	cout << "What is the name of the output file? ";
	getline(cin, fileName);

	ofstream file(fileName);

	file << results;
}