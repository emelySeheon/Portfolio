/*
Program: SeheonP7pt2
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/7/20
Purpose: To calculate rain data for Maryland in April 1989
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <fstream>;
#include <sstream>;
using namespace std;

void read(int day[], double data[]);

int main()
{

	int days[30];
	double data[30];

	read(days, data);
	for (int i = 0; i < 30;++i) {
		cout << days[i] << " " << data[i] << endl;
	}

	return 0;
}

void read(int day[], double data[])
{
	string lines[61] = {};
	int i = 0;
	int h = 0;

	//reading the file
	string text;
	string filename = "input.txt";
	ifstream inputfile;
	inputfile.open(filename);
	while (getline(inputfile, text)) {
		if (h == 0 || h == 31) {
			++h;
			continue;
		}
		lines[i] = text;
		++i;
		++h;
	}

	//putting file info in arrays
	for (int j = 0; j < 61; ++j) {
		stringstream convert(lines[j]);
		if (j < 30) {
			convert >> day[j];
		}
		else {
			convert >> data[j - 30];
		}
	}
}