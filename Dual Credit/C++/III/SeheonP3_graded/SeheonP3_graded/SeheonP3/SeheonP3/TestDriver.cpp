// Name: Emely Seheon
// Email: eseheon1@cnm.edu
// File: SeheonP3

#include "PersonGen.h"
#include "Search.h"
#include "PersonSort.h"
#include "Person.h";
#include <fstream>
#include <iostream>
#include <sstream>

using namespace std;

//TODO:  score 115/150

int main()
{
	cout << "Name: Emely Seheon" << endl;
	cout << "Program: SeheonP3" << endl;
	cout << "Objective: to search for a name in a list of person objects." << endl;

	//Declaring and initializing variables
	ifstream readFile;
	ofstream writeFile;
	int fileCount = 0;
	string lastN;
	string firstN;
	int month;
	int day;
	int year;
	string fullName;
	int num = 1000;

	//TODO:  You have a stack overflow.  That is why the spec says to make the arrays
	//TODO:  with the new operator, also the PersonGenerator -10
	Person arrOne[1000];
	PersonSort sorting;
	Search searching;
	PersonGen generating;

	//TODO:  Why are you starting at -2? 
	int initializer = -2;
	int initializerr = -2;
	int* position = &initializer;
	int* pNumCompares = &initializerr;
	bool found = false;
	string name;
	string first;
	string last;
	string yesOrNo;
	string again = "y";
	bool goAgain = true;

//TODO:  This is ok for testing during development, or use an old file.
	generating.CreateFile("test.txt", num);

	//Open File
	readFile.open("P3_Searches_Test.txt");
	if (readFile.is_open()) {
		readFile >> fileCount;
	}
	else {
		cout << "Unable to open read file";
	}

	//Read file into array
	//TODO:  The reason we have a PersonGen class is to handle Person Objects  -10
	//TODO:  This should look like: 
	//generating.UseFile("P3_Searches_Test.txt");
	//TODO:  Then, inside your loop of 1000:
	//generating.GetNewPerson();

	for (int i = 0; i < num; ++i) {
		if (i >= fileCount) {
			readFile.close();
		}
		else {
			char c;
			readFile >> lastN >> firstN >> c >> month >> c >> day >> c >> year;
			stringstream ss;
			ss << lastN << " " << firstN;
			fullName = ss.str();
			arrOne[i].SetName(fullName);
			arrOne[i].SetBirthday(month, day, year);
		}
	}

	//Displaying first 20
	writeFile.open("log.txt");
	if (readFile.is_open()) {
		readFile >> fileCount;
	}
	else {
		cout << "Unable to open write file";
	}
	for (int i = 0; i < 20; ++i) {
		cout << arrOne[i].GetName() << endl;
		writeFile << arrOne[i].GetName() << endl;
	}

	//Sort
	sorting.ShellSort(arrOne, num);

	while (goAgain) {

		//TODO:  The idea is to ask for first name and last name, like Bob White, 
		//TODO:  then you convert the string to something that can be searched for  -10
		  
		//Getting name
		cout << endl << "Please enter the first name of the person you would like to search for (capitalized): ";
		cin >> first;
		cout << "Please enter the last name of the person you would like to search for (capitalized): ";
		cin >> last;
		name = searching.FormatName(first, last);

		//Sequential
		found = searching.SequentialSearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Sequential";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Sequential" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Binary
		found = searching.BinarySearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Binary";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Binary" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Recursive
		found = searching.RunRecursiveSearch(arrOne, num, name, position, pNumCompares);
		if (found) {
			yesOrNo = "Yes";
		}
		else {
			yesOrNo = "No";
		}
		cout << endl << "Recursive";
		cout << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl;
		writeFile << endl << "Recursive" << endl << "Name: " << name << endl << "Found: " << yesOrNo << endl << "Position: " << *position << endl << "Number of Compares: " << *pNumCompares << endl << endl;

		//Go Again
		cout << "Would you like to go again? (y/n) ";
		cin >> again;
		if (again == "y") {
			goAgain = true;
		}
		else {
			goAgain = false;
		}
	}
	writeFile.close();

	return 0;
}